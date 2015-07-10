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
import java.awt.geom.Ellipse2D;
import java.util.EnumSet;

public class BallAttack implements Drawable, Collider, Stepper {

    private float color;
    private double radius;
    private double sin, cos, theta;
    private Vector position;
    private double speed;
    private boolean activatedTrapYet;

    public BallAttack (Vector origin) {
        position = origin;
        speed = Math.random() * 3 + 2;
        radius = (int) (Math.random() * 18) + 4;
        theta = Math.random() * Math.PI / 2;
        sin = Math.sin(theta);
        cos = Math.cos(theta);
    }

    public BallAttack (Vector position, Vector vector, float color) {
        speed = 1;
        sin = vector.y();
        cos = vector.x();
        this.position = position;
        radius = 11;
        this.color = color;
    }


    @Override
    public void collide (Entity entity) {
        if (!(entity instanceof Player)) {
            return;
        }
        Ellipse2D.Double self = new Ellipse2D.Double(position.x(), position.y(), radius * 2, radius * 2);
        if (self.intersects(entity.bounds().rect())) {
            System.out.println("The player has died . . .");
        }
    }

    @Override
    public void draw (Graphics2D g, Vector position) {
        g.setColor(Color.DARK_GRAY);
        g.fillOval(position.xc() - 8, position.yc() - 8, (int) radius * 2 + 16, (int) radius * 2 + 16);
        g.setColor(new Color(0, 0, color));
        g.fillOval(position.xc(), position.yc(), (int) radius * 2, (int) radius * 2);
        g.setColor(Color.GREEN);
        //double sin = Math.sin(theta - 2 * (theta - Math.PI/4));
        //double cos = Math.cos(theta - 2 * (theta - Math.PI/4));
//        Line2D UpperLine = new Line2D.Double(0, radius / sin, GameScreen.WIDTH, radius / sin + cos * GameScreen.WIDTH / sin);
//        Line2D LowerLine = new Line2D.Double(0, -radius / sin, GameScreen.WIDTH, -radius / sin + cos * GameScreen.WIDTH / sin);
//        Line2D UpperLine = new Line2D.Double(position.x(), 0, position.x(), GameScreen.HEIGHT);
//        Line2D LowerLine = new Line2D.Double(position.x() + radius * 2, 0, position.x() + radius * 2, GameScreen.HEIGHT);
//
//        g.draw(UpperLine);
//        g.draw(LowerLine);
    }

    @Override
    public BoundingBox bounds () {
        return new BoundingBox(position, new Vector(radius * 2, radius * 2));
    }

    @Override
    public strictfp void step (EnumSet<Control> keys) {
        position = position.dx(cos * speed).dy(sin * speed);
        if (position.y() > GameScreen.HEIGHT - 32 && !activatedTrapYet) {
            Level.currentLevel().activate(Event.playerJump, (int) (19 - color * 19));
            activatedTrapYet = true;
        }
        // radius = Math.signum(Math.random() - .5) * radius + Math.random() * 2;
    }
}
