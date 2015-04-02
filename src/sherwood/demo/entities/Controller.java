package sherwood.demo.entities;

import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Vector;
import sherwood.demo.structures.levels.Level;
import sherwood.demo.structures.levels.event.Event;
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
