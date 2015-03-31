package sherwood.demo.entities.particles;

import sherwood.demo.entities.Drawable;
import sherwood.demo.entities.Effect;
import sherwood.demo.entities.Mover;
import sherwood.demo.entities.Stepper;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Vector;
import sherwood.inputs.keyboard.control.Control;

import java.util.EnumSet;

public class Explosion implements Stepper, Drawable, Mover, Effect {

    private BoundingBox bounds;
    private Vector velocity;

    public Explosion(Vector position, Vector size) {
        this.bounds = new BoundingBox(position, size);
        double magnitude = Math.random() * 32;
        double theta = Math.random() * Math.PI * 2;
        this.velocity = new Vector(Math.cos(theta) * magnitude, Math.sin(theta) * magnitude);
    }

    @Override
    public Vector velocity () {
        return velocity;
    }

    @Override
    public void step (EnumSet<Control> keys) {
        bounds = bounds.over(velocity);
        velocity = velocity.sx(velocity.x() * 0.99).dy(0.18);
    }

    @Override
    public BoundingBox bounds () {
        return bounds;
    }
}
