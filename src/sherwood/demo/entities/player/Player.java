package sherwood.demo.entities.player;

import sherwood.demo.entities.Collider;
import sherwood.demo.entities.Entity;
import sherwood.demo.entities.Mover;
import sherwood.demo.entities.Stepper;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Vector;
import sherwood.inputs.keyboard.control.Control;

import java.util.EnumSet;

public class Player implements Collider, Stepper, Mover {

    private static final Vector size = new Vector(5, 5);
    private PlayerMovement movement;

    private BoundingBox bounds;

    public Player (Vector start) {
        bounds = new BoundingBox(start, size);
        movement = new PlayerMovement();
    }

    @Override
    public void step (EnumSet<Control> keys) {
        for (Control c : keys)
            movement.step(c);
    }

    @Override
    public Vector velocity () {
        return movement.velocity();
    }


    @Override
    public BoundingBox bounds () {
        return bounds;
    }

    @Override
    public void collide (Entity entity) {

    }
}
