package sherwood.demo.perfect.boss.attacks;

import sherwood.demo.game.entities.Collider;
import sherwood.demo.game.entities.Drawable;
import sherwood.demo.game.entities.Entity;
import sherwood.demo.game.entities.Stepper;
import sherwood.demo.game.entities.player.Player;
import sherwood.demo.game.graphics.SpriteBox;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;
import sherwood.demo.game.structures.levels.Level;
import sherwood.demo.game.structures.levels.event.Event;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.EnumSet;

public class CosineFruit implements Stepper, Drawable, Collider {

    private final double amplitude;
    private double time = 0;
    private BoundingBox box;
    public static boolean lastUp;
    private boolean up;

    public CosineFruit(double amplitude) {
        box = new BoundingBox(-10, GameScreen.HEIGHT - 32 * 4, 21, 22);
        this.amplitude = amplitude;
        up = lastUp = !lastUp;
    }

    @Override
    public void draw(Graphics2D g, Vector position) {
        g.setStroke(new BasicStroke(5));
        if (up) {
            g.setColor(Color.cyan);
            g.drawLine(position.xc() + 10, 0, position.xc() + 10, (int) box.y() + 10);
        } else {
            g.setColor(Color.GREEN);
            g.drawLine(position.xc() + 10, GameScreen.HEIGHT, position.xc() + 10, position.yc() + 10);
        }
        g.drawImage(SpriteBox.instance().sprite("res/img/Fruit.png"),
                position.xc(), (int) box.y(), (int) box.width(), (int) box.height(), null);
        g.setStroke(new BasicStroke(1));

    }

    @Override
    public void collide(Entity entity) {
        if (!(entity instanceof Player))
            return;
        // copy from elliptical
        int ratio = (int) (bounds().width() / 21);
        Vector position = bounds().position();
        Ellipse2D.Double self = new Ellipse2D.Double(position.xc() + ratio, position.yc() + ratio, (int) bounds().width() - 2 * ratio, (int) bounds().height() - 2 * ratio);
        if (self.intersects(Level.currentLevel().underlyingLevel().player().bounds().rect()))
            Level.currentLevel().activate(Event.playerDeath);
    }

    @Override
    public void step(EnumSet<Control> keys) {
        double adjusted = time++ / (60) * Math.PI;
        box = box.over(new Vector(Math.abs((time / 100 + 1) * amplitude * Math.sin(adjusted)), amplitude * Math.cos(adjusted)));
    }

    @Override
    public BoundingBox bounds() {
        return new BoundingBox(box.x(), up ? 0 : box.y(), 21, up ? box.y() + 22 : GameScreen.HEIGHT - box.y());
    }

    public Entity reverse() {
        this.up = !up;
        return this;
    }
}
