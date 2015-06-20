package sherwood.demo.game.structures.backpack;

import sherwood.demo.game.entities.Drawable;
import sherwood.demo.game.entities.Stepper;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Direction;
import sherwood.demo.game.physics.Vector;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.EnumSet;

public class DrawnBackpack implements Drawable, Stepper {

    private byte scrolledIn;
    private Direction scrollDirection;
    private Backpack instance;

    public DrawnBackpack() {
        this.instance = Backpack.instance();
        this.scrollDirection = Direction.RIGHT;
    }


    @Override
    public void draw (Graphics2D g, Vector position) {
        if (scrolledIn == 0) return;
        g.setColor(active() ? Color.RED : Color.WHITE);
        g.fillRect(39, 39, GameScreen.WIDTH, scrolledIn);
    }

    @Override
    public BoundingBox bounds () {
        return null;
    }

    @Override
    public void step (EnumSet<Control> keys) {
        if (keys.contains(Control.START))
            scrollDirection = scrollDirection == Direction.DOWN ? Direction.UP: Direction.DOWN;
        scrolledIn += scrollDirection.y() * 4;
        if (scrolledIn < 0)
            scrolledIn = 0;
        if (scrolledIn > 96)
            scrolledIn = 96;
    }

    public void direction(Direction direction) {
        this.scrollDirection = direction;
    }

    public boolean active() {
        return scrolledIn > 0 && scrollDirection != Direction.UP;
    }
}
