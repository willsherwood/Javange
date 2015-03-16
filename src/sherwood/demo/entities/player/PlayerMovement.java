package sherwood.demo.entities.player;

import sherwood.demo.physics.Vector;
import sherwood.demo.structures.Disposable;
import sherwood.inputs.keyboard.control.Control;

import java.util.EnumMap;
import java.util.Map;

public class PlayerMovement {

    private Map<Control, Disposable> actions;
    private Vector velocity;

    public PlayerMovement () {
        actions = new EnumMap<>(Control.class);
        actions.put(Control.LEFT, () -> velocity = velocity.dx(-4));
        actions.put(Control.RIGHT, () -> velocity = velocity.dx(4));
    }

    public void step (Control control) {
        actions.getOrDefault(control, ()->{}).go();
    }

    public Vector velocity () {
        return velocity;
    }
}
