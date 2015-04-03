package sherwood.demo.entities.baddies.triggered;

import sherwood.demo.entities.Triggered;
import sherwood.demo.entities.baddies.spike.MovingSpike;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Vector;
import sherwood.inputs.keyboard.control.Control;

import java.util.EnumSet;

public class TriggeredSpike extends MovingSpike implements Triggered {


    public TriggeredSpike (BoundingBox bounds, Vector velocity) {
        super(bounds, Vector.ZERO);
    }

    @Override
    public void trigger () {
        velocity(new Vector(0, -8));
    }

    @Override
    public void step (EnumSet<Control> keys) {
        moveTo(bounds().position().over(velocity()));
    }
}
