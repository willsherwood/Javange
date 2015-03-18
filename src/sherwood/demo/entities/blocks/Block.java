package sherwood.demo.entities.blocks;

import sherwood.demo.entities.Drawable;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Vector;

import java.awt.Color;
import java.awt.Graphics2D;

public class Block implements Drawable {

    private final BoundingBox bounds;

    public Block (Vector position, Vector size) {
        bounds = new BoundingBox(position, size);
    }

    @Override
    public BoundingBox bounds () {
        return bounds;
    }

    @Override
    public void draw (Graphics2D g, Vector position) {
        g.setColor(Color.WHITE);
        g.fill(new BoundingBox(position, bounds.size()).rect());
    }
}
