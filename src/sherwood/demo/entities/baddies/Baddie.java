package sherwood.demo.entities.baddies;

import sherwood.demo.entities.Drawable;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Vector;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Baddie implements Drawable {

    private BoundingBox bounds;

    public Baddie (BoundingBox bounds) {
        this.bounds = bounds;
    }

    @Override
    public BoundingBox bounds () {
        return bounds;
    }

    public void moveTo (Vector here) {
        bounds = new BoundingBox(here, bounds.size());
    }

    @Override
    public void draw (Graphics2D g, Vector position) {
        g.setColor(Color.RED);
        g.fill(new BoundingBox(position, bounds.size()).rect());
    }
}
