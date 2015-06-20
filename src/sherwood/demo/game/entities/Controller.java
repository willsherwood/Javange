package sherwood.demo.game.entities;

import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;
import sherwood.demo.game.structures.levels.Level;
import sherwood.demo.game.structures.levels.event.Event;
import sherwood.inputs.keyboard.control.Control;

import java.util.EnumSet;

public class Controller implements Stepper, Effect {
    @Override
    public void step (EnumSet<Control> keys) {
        if (keys.contains(Control.SELECT)) {
            Level.currentLevel().activate(Event.reset);
        }
    }

    @Override
    public BoundingBox bounds () {
        return new BoundingBox(new Vector(-99, -99), Vector.ZERO);
    }
}
