package sherwood.demo.entities.particles;

import sherwood.demo.entities.Drawable;
import sherwood.demo.entities.Mover;
import sherwood.demo.entities.Stepper;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Vector;
import sherwood.inputs.keyboard.control.Control;

import java.util.EnumSet;

public class Explosion implements Stepper, Drawable, Mover {

    private BoundingBox bounds;

    public Explosion(Vector position, Vector size) {
        this.bounds = new BoundingBox(position, size);
    }

    @Override
    public Vector velocity () {
        return null;
    }

    @Override
    public void step (EnumSet<Control> keys) {

    }

    @Override
    public BoundingBox bounds () {
        return null;
    }
}
