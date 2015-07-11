package sherwood.demo.perfect.boss.attacks;

import sherwood.demo.game.entities.Entity;
import sherwood.demo.game.entities.baddies.MovingBaddie;
import sherwood.demo.game.entities.player.Player;
import sherwood.demo.game.graphics.SpriteBox;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;
import sherwood.demo.game.structures.levels.Level;
import sherwood.demo.game.structures.levels.event.Event;
import sherwood.inputs.keyboard.control.Control;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.EnumSet;

public class Fruit extends MovingBaddie {

    public static int number;

    public Fruit (Vector position) {
        super(new BoundingBox(position, new Vector(21, 22)), new Vector(number / 18. - 3, 3 + number / 5.));
        number++;
    }

    public Fruit (Vector position, Vector velocity) {
        super(new BoundingBox(position, new Vector(21, 22)), velocity);
    }

    public Fruit (Vector position, Vector size, Vector velocity) {
        super(new BoundingBox(position, size), velocity);
    }

    @Override
    public void step (EnumSet<Control> keys) {
        moveTo(bounds().over(velocity()).position());
    }

    @Override
    public void collide (Entity entity) {
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
    public void draw (Graphics2D g, Vector position) {
        g.drawImage(SpriteBox.instance().sprite("res/img/Fruit.png"),
                position.xc(), position.yc(), (int) bounds().width(), (int) bounds().height(), null);

        // test hit box
//        g.setColor(Color.YELLOW);
//        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
//        int ratio = (int) (bounds().width() / 21);
//        g.fillOval(position.xc() + ratio, position.yc() + ratio, (int) bounds().width() - 2 * ratio, (int) bounds().height() - 2 * ratio);
//        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }
}
