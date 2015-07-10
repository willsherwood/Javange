package sherwood.demo.game.layout;

import sherwood.demo.game.entities.Collider;
import sherwood.demo.game.entities.Entity;
import sherwood.demo.game.entities.blocks.Block;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;
import sherwood.demo.game.structures.graph.Graph;
import sherwood.demo.game.structures.graph.HighRunningTime;
import sherwood.demo.game.structures.graph.UUGraph;
import sherwood.gameScreen.GameScreen;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A contiguous layout is a layout of tangent blocks.
 * each block's size and position MUST be a ratio of the grid size.
 */
public class ContiguousLayout implements Layout {

    private Graph<BoundingBox> graph;
    private BoundingBox fullBounds;
    private int gridSize;

    public ContiguousLayout (int gridSize) {
        this.graph = new UUGraph<>();
        this.fullBounds = null;
        this.gridSize = gridSize;
    }

    /**
     * condition: this solid bound will not be added if it is not tangent to another bound
     */
    @Override
    public void add (BoundingBox solidBounds) {
        if (graph.vertices().size() > 0) {
            Set<BoundingBox> connected = graph.vertices().stream().filter(box -> tangent(box, solidBounds)).collect(Collectors.toSet());
            connected.forEach(a -> graph.connect(a, solidBounds));
        }
        //graph.vertices().stream().filter(box -> tangent(box, solidBounds)).forEach(box -> graph.connect(box, solidBounds));
        // ok how will I do this
        else {
            graph.connect(solidBounds);
        }
        // we need to update the bounds at this point
        fullBounds = expandedUnion(fullBounds, solidBounds);
    }

    private boolean tangent (BoundingBox A, BoundingBox B) {
        //TODO: feels like a hack -- works diagonally
        return new BoundingBox(A.x() - 1, A.y() - 1, A.width() + 2, A.height() + 2).intersects(B);
    }

    private BoundingBox expandedUnion (BoundingBox A, BoundingBox B) {
        if (A == null)
            return B;
        double x1 = Math.min(A.x(), B.x());
        double x2 = Math.max(A.x() + A.width(), B.x() + B.width());
        double y1 = Math.min(A.y(), B.y());
        double y2 = Math.max(A.y() + A.height(), B.y() + B.height());
        return new BoundingBox(x1, y1, x2 - x1, y2 - y1);
    }

    @Override
    @HighRunningTime(complexity = HighRunningTime.RunningTime.POLYNOMIAL)
    public void remove (Vector position) {
        // this has a really high running time :-(
        // becuase now we have to go through AGAIN and remove edges
        BoundingBox p = new BoundingBox(position.x(), position.y(), 2, 2);
        Set<BoundingBox> toRemove = graph.vertices().stream().filter(a -> a.intersects(p)).collect(Collectors.toSet());
        toRemove.forEach(graph::remove);
    }

    @Override
    public Set<Block> blocks () {
        Set<Block> blocks = new HashSet<>();
        graph.vertices().stream().forEach(a -> blocks.add(new Block(a.position(), a.size())));
        return blocks;
    }

    @Override
    public void collide (Entity entity) {
        // now we have to tell the player to collide with the block... this may be o(n)
        // that's not good
        // is there a better way . . .
        // yes there is. Next commit will fix this
        if (entity instanceof Collider) {
            ((Collider) entity).collide(this);
        }
    }

    @Override
    public BoundingBox bounds () {
        return fullBounds == null ? new BoundingBox(Vector.ZERO, Vector.ZERO) : fullBounds;
    }

    @Override
    public void draw (Graphics2D g, Vector position) {
        //TODO: we need to smoothly connect these edges . . .

        // vertices:
        drawVertices(g);

        // edges:
        g.setColor(Color.GREEN);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
        //graph.edges().forEach(a -> g.drawLine((int) (a.first().x() + a.first().width() / 2), (int) (a.first().y() + a.first().height() / 2), (int) (a.second().x() + a.second().width() / 2), (int) (a.second().y() + a.second().height() / 2)));
        graph.edges().forEach(a -> g.drawLine(
                (int) (a.first().x() + a.first().width() / 2),
                (int) (a.first().y() + a.first().height() / 2),
                (int) (a.second().x() + a.second().width() / 2),
                (int) (a.second().y() + a.second().height() / 2)));
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        // full bounds
//        g.setColor(Color.blue);
//        g.draw(fullBounds.rect());
    }

    private void drawVertices (Graphics2D g) {
        boolean[][] blocks = new boolean[GameScreen.HEIGHT / gridSize][GameScreen.WIDTH / gridSize];
        Map<Vector, BlockState> todraw = new HashMap<>();
        for (BoundingBox box : graph.vertices()) {
            for (int r = 0; r < box.height() / gridSize; r++)
                for (int c = 0; c < box.width() / gridSize; c++)
                    blocks[r + (int) box.y() / gridSize][c + (int) box.x() / gridSize] = true;
        }

        // check horizontal
        for (int r = 0; r < GameScreen.HEIGHT / gridSize; r++) {
            boolean seen = false;
            // why is it saying seen is always false ?
            for (int c = 0; c < GameScreen.WIDTH / gridSize; c++) {
                if (!seen && blocks[r][c]) {
                    seen = true;
                    todraw.put(new Vector(r, c), BlockState.SOLID);
                } else if (c >= GameScreen.WIDTH / gridSize - 1) {
                    //todraw.put(new Vector(r, c), BlockState.SOLID);
                } else if (seen && !blocks[r][c + 1] && blocks[r][c]) {
                    todraw.put(new Vector(r, c), BlockState.SOLID);
                    seen = false;
                } else if (blocks[r][c]) {
                    todraw.put(new Vector(r, c), BlockState.HORIZONTAL);
                    blocks[r][c] = false;
                }

            }


        }

        // check vertical
        // TODO: fix vertical . . .
        for (int c = 0; c < GameScreen.WIDTH / gridSize; c++) {
            boolean seen = false;
            for (int r = 1; r < GameScreen.HEIGHT / gridSize - 1; r++) {
                if (!seen && blocks[r][c]) {
                    seen = true;
                } else if (seen && r < GameScreen.HEIGHT / gridSize - 1 && !blocks[r + 1][c]) {
                    seen = false;
                } else {
                    boolean vertical = true;
                    for (int dy = -1; dy <= 1; dy++)
                        for (int dx = -1; dx <= 1; dx++) {
                            if (Math.abs(dx) == Math.abs(dy))
                                continue;
                            if (todraw.get(new Vector(r, c)) == (BlockState.HORIZONTAL))
                                vertical = false;
                            if (r != 0 && todraw.get(new Vector(r, c)) != BlockState.SOLID)
                                vertical = false;
                        }
                    if (vertical)
                        todraw.put(new Vector(r, c), BlockState.VERTICAL);
                }
            }
            // might work
        }

        g.setColor(Color.YELLOW);
        todraw.forEach((a, b) -> {

            switch (b) {
                case HORIZONTAL:
                    g.drawLine(a.yc() * gridSize, a.xc() * gridSize, a.yc() * gridSize + gridSize, a.xc() * gridSize);
                    g.drawLine(a.yc() * gridSize, gridSize + a.xc() * gridSize, a.yc() * gridSize, a.xc() * gridSize + gridSize);
                    break;
                case VERTICAL:
                    g.drawLine(a.yc() * gridSize, a.xc() * gridSize, a.yc() * gridSize, a.xc() * gridSize + gridSize);
                    g.drawLine(a.yc() * gridSize + gridSize, a.xc() * gridSize, gridSize + a.yc() * gridSize, a.xc() * gridSize + gridSize);
                    break;
                case SOLID:
                    g.drawRect(a.yc() * gridSize, a.xc() * gridSize, gridSize, gridSize);
                    break;
            }
        });
    }
}


//TODO: add bounding boxes to the collision factory