package sherwood.demo.entities.player;

import sherwood.demo.entities.Collider;
import sherwood.demo.entities.Entity;
import sherwood.demo.entities.Mover;
import sherwood.demo.entities.Stepper;
import sherwood.demo.entities.baddies.Baddie;
import sherwood.demo.entities.baddies.Spike;
import sherwood.demo.entities.blocks.Block;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Direction;
import sherwood.demo.physics.Vector;
import sherwood.inputs.keyboard.control.Control;

import java.util.EnumSet;

public class Player implements Collider, Stepper, Mover {

    private PlayerMovement movement;

    public Player (Vector start) {
        movement = new PlayerMovement(start);
    }

    @Override
    public void step (EnumSet<Control> keys) {
        movement.step(keys);
        movement.addVelocity();
    }

    @Override
    public Vector velocity () {
        return movement.velocity();
    }


    @Override
    public BoundingBox bounds () {
        return movement.bounds();
    }

    @Override
    public void collide (Entity entity) {
        if (entity instanceof Baddie) {
            if (entity instanceof Spike) {
                // if its a spike we need to check to see if they are really colliding
                // because spike is represented by a square bounding box however
                // it still has a triangular shape
                Spike s = (Spike) entity;
                if (!s.poly().intersects(bounds().x(), bounds().y(), bounds().width(), bounds().height()))
                    return;
            }
            // uh-oh! We're dead.
            die();
        }
        if (entity instanceof Block) {
            if (movement.bounds().over(movement.velocity().zx().negate()).intersects(entity.bounds())) {
                // would have collided with the block even if we weren't moving horizontally
                movement.horizontalCollision(entity);
            } else {
                movement.verticalCollision(entity);
            }
        }
    }

    private void die () {
        System.out.println("we're dead");
    }

    public Direction direction () {
        return movement.direction();
    }
}
