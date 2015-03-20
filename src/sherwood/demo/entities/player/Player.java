package sherwood.demo.entities.player;

import sherwood.demo.entities.*;
import sherwood.demo.entities.baddies.Baddie;
import sherwood.demo.entities.baddies.MovingSpike;
import sherwood.demo.entities.baddies.Spike;
import sherwood.demo.entities.blocks.Block;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Direction;
import sherwood.demo.physics.Vector;
import sherwood.demo.structures.levels.Level;
import sherwood.demo.structures.levels.event.Event;
import sherwood.inputs.keyboard.control.Control;

import java.awt.Graphics2D;
import java.util.EnumSet;

public class Player implements Collider, Stepper, Mover, Drawable {

    private PlayerMovement movement;
    private PlayerSprite sprite;

    public Player (Vector start) {
        movement = new PlayerMovement(start);
        sprite = new PlayerSprite(this);
    }

    @Override
    public void step (EnumSet<Control> keys) {
        sprite.step();
        movement.step(keys);
        movement.addVelocity();
        if (keys.contains(Control.SELECT)) {
            die();
        }
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
            if (entity instanceof MovingSpike) {
                MovingSpike m = (MovingSpike) entity;
                if (m.poly().intersects(bounds().x(), bounds().y(), bounds().width(), bounds().height())) {
                    die();
                    return;
                } else return;
            }
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

    @Override
    public void draw (Graphics2D g, Vector position) {
        sprite.draw(g, position);
    }

    private void die () {
        Level.currentLevel().activate(Event.playerDeath);
    }

    public Direction direction () {
        return movement.direction();
    }
}
