package sherwood.demo.game.entities.particles;

import sherwood.demo.game.entities.Drawable;
import sherwood.demo.game.entities.Effect;
import sherwood.demo.game.entities.Mover;
import sherwood.demo.game.entities.Stepper;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;
import sherwood.inputs.keyboard.control.Control;

import java.awt.*;
import java.util.EnumSet;

public class Explosion implements Stepper, Drawable, Mover, Effect {

    private BoundingBox bounds;
    private Vector velocity;
    private Color col;

    public Explosion(Vector position, Vector size) {
        this.bounds = new BoundingBox(position, size);
        double magnitude = Math.random() * 32;
        double theta = Math.random() * Math.PI * 2;
        this.velocity = new Vector(Math.cos(theta) * magnitude, Math.sin(theta) * magnitude);
        this.col = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
    }

    @Override
    public void draw(Graphics2D g, Vector position) {
        g.setColor(col);
        for (int i = 0; i < 3; i++) {
            position = position.over(new Vector(Math.random(), Math.random()));
            g.fillOval(position.xc(), position.yc(), (int)(Math.random() * 3), (int)(Math.random() * 3));
        }
        // col = Math.random() > 0.4 ? col.darker() : col.brighter();
    }

    @Override
    public Vector velocity() {
        return velocity;
    }

    @Override
    public void step(EnumSet<Control> keys) {
        bounds = bounds.over(velocity);
        velocity = velocity.sx(velocity.x() * 0.99).dy(0.18);
    }

    @Override
    public BoundingBox bounds() {
        return bounds;
    }
}
