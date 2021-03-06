package sherwood.demo.game.entities.player;

import sherwood.demo.game.entities.Entity;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Direction;
import sherwood.demo.game.physics.Vector;
import sherwood.demo.game.structures.Disposable;
import sherwood.inputs.keyboard.control.Control;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

public class PlayerMovement {

    public static final Vector size = new Vector(11, 21);
    private Map<Control, Disposable> actions;
    private Vector velocity;
    private Vector position;
    private Jumper jumper;
    private Direction direction = Direction.RIGHT;

    public PlayerMovement(Vector start) {
        actions = new EnumMap<>(Control.class);
        velocity = Vector.ZERO;
        actions.put(Control.LEFT, () -> {
            velocity = velocity.sx(-3);
            direction = Direction.LEFT;
        });
        actions.put(Control.RIGHT, () -> {
            velocity = velocity.sx(3);
            direction = Direction.RIGHT;
        });
        position = start;
        jumper = new Jumper(this);
    }

    public void step(EnumSet<Control> controls) {
        velocity = velocity.sx(0);
        velocity = velocity.dy(.3).cy(9);
        controls.forEach(a -> actions.getOrDefault(a, () -> {
        }).go());
        if (controls.contains(Control.A)) {
            jumper.jump();
        } else {
            jumper.stop();
        }
    }

    public Vector velocity() {
        return velocity;
    }

    public void verticalCollision(Entity entity) {
        if (velocity.y() >= 0) /* falling */ {
            position = new Vector(position.x(), entity.bounds().position().y() - bounds().height());
            jumper.reset();
            velocity = velocity.sy(0);
            return;
        }
        position = new Vector(position.x(), entity.bounds().position().y() + entity.bounds().height());
        velocity = velocity.sy(0);
    }

    public void horizontalCollision(Entity entity) {
        if (velocity.x() > 0) {
            position = new Vector(entity.bounds().position().x() - bounds().width(), position.y());
            velocity = velocity.sx(0);
            return;
        }
        position = new Vector(entity.bounds().position().x() + entity.bounds().width(), position.y());
        velocity = velocity.sx(0);
    }

    public BoundingBox bounds() {
        return new BoundingBox(position, size);
    }

    /**
     * changes the position to one step of velocity
     */
    public void addVelocity() {
        position = position.over(velocity);
    }

    /**
     * changes this.velocity to velocity
     */
    public void changeVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public Direction direction() {
        return direction;
    }
}
