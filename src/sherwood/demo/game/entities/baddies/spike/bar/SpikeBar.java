package sherwood.demo.game.entities.baddies.spike.bar;

import sherwood.demo.game.entities.Drawable;
import sherwood.demo.game.entities.baddies.Baddie;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;

import java.awt.Graphics2D;

public class SpikeBar extends Baddie {

    public SpikeBar (Vector position) {
        super(new BoundingBox(position, new Vector(32, 108)));
    }

    public SpikeBar(BoundingBox bounds) {
        super(bounds);
    }

    @Override
    public void draw (Graphics2D g, Vector position) {
        Drawable.paint(g, position, "SpikeBar");
    }
}
