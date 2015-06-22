package sherwood.demo.game.layout;

import sherwood.demo.game.entities.Entity;
import sherwood.demo.game.entities.blocks.Block;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;
import sherwood.demo.game.structures.levels.Level;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;

public class ContiguousLayout implements Layout {

    private Set<Block> blocks;
    private BoundingBox fullBounds;

    public ContiguousLayout () {
        this.blocks = new HashSet<>();
        this.fullBounds = null;
    }

    @Override
    public void add (BoundingBox solidBounds) {
        blocks.add(new Block(solidBounds.position(), solidBounds.size()));
        // we need to update the bounds at this point
        fullBounds = expandedUnion(fullBounds, solidBounds);
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
    public void remove (Vector position) {
        Set<Block> toRemove = new HashSet<>();
        blocks.stream().filter(b -> b.bounds().intersects(new BoundingBox(position, new Vector(3, 3)))).forEach(toRemove::add);
        blocks.removeAll(toRemove);
    }

    @Override
    public void collide (Entity entity) {
        Level.currentLevel().underlyingLevel().player();
    }

    @Override
    public BoundingBox bounds () {
        return fullBounds == null ? new BoundingBox(Vector.ZERO, Vector.ZERO) : fullBounds;
    }

    @Override
    public void draw (Graphics2D g, Vector position) {
        //TODO: we need to smoothly connect these edges . . .
        blocks.forEach(a->a.draw(g, a.bounds().position()));
        g.setColor(Color.RED);
        g.draw(fullBounds.rect());
    }
}
