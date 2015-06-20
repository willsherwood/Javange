package sherwood.demo.game.entities.baddies.triggered;

import sherwood.demo.game.entities.Triggered;
import sherwood.demo.game.entities.baddies.spike.MovingSpike;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;
import sherwood.inputs.keyboard.control.Control;

import java.awt.Polygon;
import java.util.EnumSet;

public class TriggeredSpike extends MovingSpike implements Triggered {


    private final Vector velocity;
    private boolean triggered;

    public TriggeredSpike (BoundingBox bounds, Vector velocity) {
        super(bounds, Vector.ZERO);
        this.velocity = velocity;
    }

    @Override
    public void trigger () {
        triggered = true;
        velocity(velocity);
    }

    @Override
    public void step (EnumSet<Control> keys) {
        moveTo(bounds().position().over(velocity()));
    }

    @Override
    public Polygon poly () {
        if (triggered)
            return super.poly();
        return new Polygon(new int[]{-111}, new int[]{-111}, 1);
    }
}
