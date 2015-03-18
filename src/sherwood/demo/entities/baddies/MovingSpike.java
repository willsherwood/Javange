package sherwood.demo.entities.baddies;

import sherwood.demo.entities.Drawable;
import sherwood.demo.entities.Entity;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Direction;
import sherwood.demo.physics.Vector;
import sherwood.inputs.keyboard.control.Control;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.EnumSet;

public class MovingSpike extends MovingBaddie {

    private final Direction face;
    private Vector initialPosition;

    public MovingSpike (BoundingBox bounds, Vector velocity) {
        super(bounds, velocity);
        this.face = Direction.UP;
        this.initialPosition = bounds.position();
    }

    public Polygon poly () {
        int[] x, y;
        if (face.vertical()) {
            x = new int[]{bounds().position().xc(), bounds().position().xc() + bounds().size().xc() / 2, bounds().position().xc() + bounds().size().xc()};
            y = new int[]{bounds().position().yc() + bounds().size().yc(), bounds().position().yc(), bounds().position().yc() + bounds().size().yc()};
            if (face == Direction.DOWN) {
                for (int i = 0; i < 3; i++)
                    y[i] += (i == 1 ? 1 : -1) * bounds().size().yc();
            }
            return new Polygon(x, y, 3);
        }
        x = new int[]{bounds().position().xc(), bounds().position().xc() + bounds().size().xc(), bounds().position().xc()};
        y = new int[]{bounds().position().yc(), bounds().position().yc() + bounds().size().yc() / 2, bounds().position().yc() + bounds().size().yc()};
        if (face == Direction.LEFT) {
            for (int i = 0; i < 3; i++)
                x[i] += (i == 1 ? -1 : 1) * bounds().size().xc();
        }
        return new Polygon(x, y, 3);
    }

    @Override
    public void step (EnumSet<Control> keys) {
        moveTo(bounds().position().over(velocity()));
        if (bounds().position().y() < initialPosition.y()) {
            velocity(velocity().negate());
            moveTo(initialPosition);
        } else if (bounds().position().y() > initialPosition.y() + bounds().height()) {
            velocity(velocity().negate());
            moveTo(initialPosition.over(bounds().size().sx(0)));
        }
    }

    @Override
    public void collide (Entity entity) {
    }

    @Override
    public void draw (Graphics2D g, Vector position) {
        Drawable.tile(g, position, this, "Spike");
    }
}
