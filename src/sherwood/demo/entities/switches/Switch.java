package sherwood.demo.entities.switches;

import sherwood.demo.entities.Drawable;
import sherwood.demo.entities.Entity;
import sherwood.demo.entities.Stepper;
import sherwood.demo.entities.Triggered;
import sherwood.demo.entities.player.Player;
import sherwood.demo.entities.trigger.CollisionTrigger;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Vector;
import sherwood.inputs.keyboard.control.Control;

import java.awt.*;
import java.util.Collection;
import java.util.EnumSet;

public class Switch extends CollisionTrigger implements Drawable, Stepper {

    private boolean on;
    private int cooldown;

    public Switch(BoundingBox position, Collection<Triggered> things) {
        super(position, things);
    }

    public Switch(BoundingBox position, Triggered thing) {
        super(position, thing);
    }

    @Override
    public void collide(Entity entity) {
        if (cooldown == 0 && entity instanceof Player) {
            super.collide(entity);
            on = !on;
            cooldown = 35;
        }
    }

    @Override
    public void draw(Graphics2D g, Vector position) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g.setColor(on ? Color.GREEN : Color.RED);
        g.fillOval(position.xc(), position.yc(), (int) bounds().width(), (int) bounds().height());
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    @Override
    public void step(EnumSet<Control> keys) {
        cooldown -= 1;
        if (cooldown < 0)
            cooldown = 0;
    }
}
