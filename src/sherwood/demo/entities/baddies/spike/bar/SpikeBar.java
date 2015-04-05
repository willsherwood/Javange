package sherwood.demo.entities.baddies.spike.bar;

import sherwood.demo.entities.Drawable;
import sherwood.demo.entities.baddies.Baddie;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Vector;

import java.awt.Graphics2D;

public class SpikeBar extends Baddie {

    public SpikeBar (Vector position) {
        super(new BoundingBox(position, new Vector(32, 108)));
    }

    @Override
    public void draw (Graphics2D g, Vector position) {
        Drawable.paint(g, position, "SpikeBar");
    }
}
