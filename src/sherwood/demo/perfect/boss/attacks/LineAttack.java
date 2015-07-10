package sherwood.demo.perfect.boss.attacks;

import sherwood.demo.game.entities.Collider;
import sherwood.demo.game.entities.Drawable;
import sherwood.demo.game.entities.Entity;
import sherwood.demo.game.entities.Stepper;
import sherwood.demo.game.entities.player.Player;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;
import sherwood.inputs.keyboard.control.Control;

import java.awt.Graphics2D;
import java.util.EnumSet;

public class LineAttack implements Drawable, Collider, Stepper {

    private double angle, maxAngle;

    public LineAttack (Vector origin) {
    }

    @Override
    public void collide (Entity entity) {
        if (!(entity instanceof Player)) {
            return;
        }
    }

    @Override
    public void draw (Graphics2D g, Vector position) {
    }

    @Override
    public BoundingBox bounds () {
        return null;
    }

    @Override
    public strictfp void step (EnumSet<Control> keys) {
    }
}
