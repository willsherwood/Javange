package sherwood.demo.game.entities.baddies.wire;

import sherwood.demo.game.entities.Drawable;
import sherwood.demo.game.entities.baddies.Baddie;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;

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
