package sherwood.demo.entities.player;

import sherwood.demo.entities.Entity;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Vector;
import sherwood.demo.structures.Disposable;
import sherwood.inputs.keyboard.control.Control;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

public class PlayerMovement {

    private Map<Control, Disposable> actions;
    private Vector velocity;
    private Vector position;

    private static final Vector size = new Vector(5, 5);

    public PlayerMovement (Vector start) {
        actions = new EnumMap<>(Control.class);
        velocity = Vector.ZERO;
        actions.put(Control.LEFT, () -> velocity = velocity.sx(-4));
        actions.put(Control.RIGHT, () -> velocity = velocity.sx(4));
        position = start;
    }

    public void step (EnumSet<Control> controls) {
        velocity = velocity.sx(0);
        velocity = velocity.dy(.1).cy(4);
        controls.forEach(a -> actions.getOrDefault(a, () -> {}).go());
    }

    public Vector velocity () {
        return velocity;
    }

    public void verticalCollision (Entity entity) {
        // TODO: only works for negative y-velocity
        position = new Vector(position.x(), entity.bounds().position().y() - size.y());
        velocity = velocity.sy(0);
    }

    public BoundingBox bounds () {
        return new BoundingBox(position, size);
    }

    public void addVelocity () {
        position = position.over(velocity);
    }
}
