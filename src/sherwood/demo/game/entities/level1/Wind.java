package sherwood.demo.game.entities.level1;

import sherwood.demo.game.entities.Collider;
import sherwood.demo.game.entities.Drawable;
import sherwood.demo.game.entities.Entity;
import sherwood.demo.game.entities.Stepper;
import sherwood.demo.game.entities.player.Player;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;

import java.awt.Graphics2D;
import java.util.EnumSet;

public class Wind implements Stepper, Collider, Drawable {

    private BoundingBox bounds;
    private int dy;

    public Wind(Vector position) {
        bounds = new BoundingBox(position, new Vector(64, GameScreen.HEIGHT));
    }

    @Override
    public void draw (Graphics2D g, Vector position) {
        Drawable.paint(g, position.dy(dy), "Wind");
        Drawable.paint(g, position.dy(dy - GameScreen.HEIGHT), "Wind");
        dy += 6;
        dy %= GameScreen.HEIGHT;
    }

    @Override
    public void collide (Entity entity) {
        if (entity instanceof Player) {
            Player p = (Player) entity;
            if (p.velocity().y() < -1)
                p.velocity(p.velocity().dy(-p.velocity().y() / 2));
        }
    }

    @Override
    public void step (EnumSet<Control> keys) {

    }

    @Override
    public BoundingBox bounds () {
        return bounds;
    }
}
