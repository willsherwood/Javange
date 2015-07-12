package sherwood.demo.perfect.boss.attacks;

import sherwood.demo.game.entities.Collider;
import sherwood.demo.game.entities.Drawable;
import sherwood.demo.game.entities.Entity;
import sherwood.demo.game.entities.Stepper;
import sherwood.demo.game.entities.player.Player;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;
import sherwood.demo.game.structures.levels.Level;
import sherwood.demo.game.structures.levels.event.Event;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.EnumSet;

public class LineAttack implements Drawable, Collider, Stepper {

    private Vector origin, goThrough;
    private double angle, maxAngle;
    private boolean goRight;

    public LineAttack (Vector origin, Vector goThrough, boolean goRight) {
        this.origin = origin;
        this.goThrough = goThrough;
        this.goRight = goRight;
        maxAngle = (goRight ? Math.PI : -Math.PI) + Math.atan2(origin.y() - goThrough.y(), origin.x() - goThrough.x());
    }

    @Override
    public void collide (Entity entity) {
        if (!(entity instanceof Player)) {
            return;
        }
        double x2 = origin.x() + Math.cos(angle) * 10000;
        double y2 = origin.y() + Math.sin(angle) * 10000;
        Polygon outer;
        if (!goRight && angle < Math.PI)
            outer = new Polygon(new int[]{origin.xc(), GameScreen.WIDTH, GameScreen.WIDTH, 0, (int) x2}, new int[]{origin.yc(), origin.yc(), 0, 0, (int) y2}, 5);
        else
            outer = new Polygon(new int[]{origin.xc(), GameScreen.WIDTH, GameScreen.WIDTH, (int) x2}, new int[]{origin.yc(), origin.yc(), goRight ? GameScreen.HEIGHT : 0, (int) y2}, 4);
        if (outer.intersects(Level.currentLevel().underlyingLevel().player().bounds().rect())) {
            Level.currentLevel().activate(Event.playerDeath);
        }

    }

    @Override
    public void draw (Graphics2D g, Vector position) {
        double x2 = origin.x() + Math.cos(angle) * 10000;
        double y2 = origin.y() + Math.sin(angle) * 10000;
        Polygon outer;
        if (!goRight && angle < Math.PI)
            outer = new Polygon(new int[]{origin.xc(), GameScreen.WIDTH, GameScreen.WIDTH, 0, (int) x2}, new int[]{origin.yc(), origin.yc(), 0, 0, (int) y2}, 5);
        else
            outer = new Polygon(new int[]{origin.xc(), GameScreen.WIDTH, GameScreen.WIDTH, (int) x2}, new int[]{origin.yc(), origin.yc(), goRight ? GameScreen.HEIGHT : 0, (int) y2}, 4);
        g.setColor(Color.BLACK);
        g.fill(outer);
        g.setColor(Color.GREEN);
        g.drawLine(origin.xc(), origin.yc(), (int) x2, (int) y2);
    }

    @Override
    public BoundingBox bounds () {
        return new BoundingBox(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
    }

    @Override
    public void step (EnumSet<Control> keys) {
        angle += goRight ? 0.15 / 7 : -0.35 / 7;
        if (!goRight) {
            if (Math.abs(angle) > Math.abs(maxAngle))
                angle = maxAngle;
        } else if (angle > maxAngle)
            angle = maxAngle;
    }

    public void move(Vector newPosition) {
        this.origin = newPosition;
        maxAngle = (goRight ? Math.PI : -Math.PI) + Math.atan2(origin.y() - goThrough.y(), origin.x() - goThrough.x());
    }

    public Vector position () {
        return origin;
    }
}
