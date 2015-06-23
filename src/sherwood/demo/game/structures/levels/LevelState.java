package sherwood.demo.game.structures.levels;

import sherwood.demo.game.physics.Vector;
import sherwood.demo.game.structures.backpack.DrawnBackpack;
import sherwood.demo.game.structures.levels.event.Event;
import sherwood.inputs.keyboard.control.Control;
import sherwood.screenStates.ScreenState;

import java.awt.Graphics2D;
import java.util.EnumSet;

public abstract class LevelState extends ScreenState {

    private DrawnBackpack pack;

    public LevelState () {
        pack = new DrawnBackpack();
    }

    /**
     * execute the event
     */
    public abstract void activate (Event event);

    @Override
    public void draw (Graphics2D g) {
        pack.draw(g, Vector.ZERO);
    }

    @Override
    public void step (EnumSet<Control> keys) {
        pack.step(keys);
    }

    public Level underlyingLevel() {
        throw new RuntimeException("This level (" + getClass() + ") does not have an underlying structure . . .");
    }
}
