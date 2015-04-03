package sherwood.demo.entities.baddies.wire;

import sherwood.demo.entities.Drawable;
import sherwood.demo.entities.baddies.Baddie;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Vector;

import java.awt.Graphics2D;

public class Wire extends Baddie {

    public Wire (Vector position, int width) {
        super(new BoundingBox(position, new Vector(width, 8)));
    }

    @Override
    public void draw (Graphics2D g, Vector position) {
        Drawable.paint(g, position, "Wire");
    }
}
