package sherwood.demo.game.layout;

import sherwood.demo.game.entities.Collider;
import sherwood.demo.game.entities.Entity;
import sherwood.demo.game.entities.blocks.Block;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;
import sherwood.demo.game.structures.graph.Graph;
import sherwood.demo.game.structures.graph.HighRunningTime;
import sherwood.demo.game.structures.graph.UUGraph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Set;
import java.util.stream.Collectors;

public class ContiguousLayout implements Layout {

    private Graph<BoundingBox> graph;
    private BoundingBox fullBounds;

    public ContiguousLayout () {
        this.graph = new UUGraph<>();
        this.fullBounds = null;
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
        //TODO: feels like a hack
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
        return null;
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
        g.setColor(Color.RED);
        graph.vertices().forEach(a -> a.draw(g));

        // edges:
        g.setColor(Color.GREEN);
        graph.edges().forEach(a-> g.drawLine((int)(a.first().x() + a.first().width() / 2), (int)(a.first().y() + a.first().height() / 2), (int)(a.second().x()+a.second().width() / 2), (int)(a.second().y()+ a.second().height() / 2)));

        // full bounds
        g.setColor(Color.blue);
        g.draw(fullBounds.rect());
    }
}

//TODO: add bounding boxes to the collision factory