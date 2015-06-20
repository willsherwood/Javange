package sherwood.demo.game.entities.switches;

import sherwood.demo.game.entities.Triggered;
import sherwood.demo.game.entities.baddies.spike.Spike;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Direction;
import sherwood.demo.game.physics.Vector;

import java.awt.*;

public class SwitchingSpike extends Spike implements Triggered {

    private boolean on;

    public SwitchingSpike(BoundingBox bounds, Direction face, boolean startsOn) {
        super(bounds, face);
        this.on = startsOn;
    }

    @Override
    public void draw(Graphics2D g, Vector position) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, on ? 1f : 0.35f));
        super.draw(g, position);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public boolean isOn() {
        return on;
    }

    @Override
    public void trigger() {
        on = !on;
    }
}
