package sherwood.demo.game.entities.baddies.wraparound;

import sherwood.demo.game.entities.Entity;
import sherwood.demo.game.entities.baddies.MovingBaddie;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;

import java.awt.*;
import java.util.EnumSet;

public class WrappingOrb extends MovingBaddie {
    public WrappingOrb(BoundingBox bounds, Vector velocity) {
        super(bounds, new Vector(0, 1.75));
    }

    @Override
    public void step(EnumSet<Control> keys) {
        moveTo(bounds().position().over(velocity()));
        moveTo(bounds().position().my(GameScreen.HEIGHT));
    }

    @Override
    public void draw(Graphics2D g, Vector position) {
        g.setColor(Color.BLUE);
        g.fillOval((int)bounds().x(), (int)bounds().y(), (int)bounds().width(), (int)bounds().height());
    }

    @Override
    public void collide(Entity entity) {

    }
}
