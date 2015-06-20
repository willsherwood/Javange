package sherwood.demo.game.entities.baddies.spike;

import sherwood.demo.game.entities.Triggered;
import sherwood.demo.game.entities.baddies.MovingBaddie;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;
import sherwood.inputs.keyboard.control.Control;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.EnumSet;

public class JumperSpike extends MovingBaddie implements Triggered {

    /**
     * moving direction. 1 if its moving down, 0 if its still, and -1 if its moving up
     */
    private int md;

    /**
     * whether or not we moved up last time
     */
    private boolean lastUp;

    /**
     * distance moved
     */
    private int moved;

    public JumperSpike (BoundingBox bounds, boolean startUp) {
        super(bounds, Vector.ZERO);
        lastUp = startUp;
    }

    @Override
    public Vector velocity () {
        return new Vector(0, md * 8);
    }

    @Override
    public void step (EnumSet<Control> keys) {
        if (moved == 32) {
            moved = 0;
            md = 0;
            lastUp = !lastUp;
            return;
        }
        moveTo(bounds().position().over(velocity()));
        moved += Math.abs(velocity().y());
    }

    @Override
    public void trigger () {
        md = lastUp ? 1 : -1;
    }

    @Override
    public void draw (Graphics2D g, Vector position) {
        g.setColor(Color.GRAY);
        g.fillPolygon(poly());
        //Drawable.paint(g, position, "Spike");
    }

    public Polygon poly () {
        if (moved == 0 && !lastUp) {
            int[] x, y;
            x = new int[]{bounds().position().xc() + 8, bounds().position().xc() + bounds().size().xc() / 2, bounds().position().xc() + bounds().size().xc() - 8};
            y = new int[]{bounds().position().yc() + bounds().size().yc() - 8, bounds().position().yc() + 8, bounds().position().yc() + bounds().size().yc() - 8};
            return new Polygon(x, y, 3);
        }
        int[] x, y;
        x = new int[]{bounds().position().xc(), bounds().position().xc() + bounds().size().xc() / 2, bounds().position().xc() + bounds().size().xc()};
        y = new int[]{bounds().position().yc() + bounds().size().yc(), bounds().position().yc(), bounds().position().yc() + bounds().size().yc()};
        return new Polygon(x, y, 3);
    }
}
