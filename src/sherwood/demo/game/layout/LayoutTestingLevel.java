package sherwood.demo.game.layout;

import sherwood.demo.game.entities.Drawable;
import sherwood.demo.game.entities.blocks.Block;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;
import sherwood.demo.game.structures.levels.HardViewportLevel;
import sherwood.demo.game.structures.levels.Level;
import sherwood.demo.game.structures.levels.LevelState;
import sherwood.demo.game.structures.levels.event.Event;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;

import java.awt.Graphics2D;
import java.util.EnumSet;

public class LayoutTestingLevel extends LevelState {

    private Level level;

    public LayoutTestingLevel () {
        reset();
    }

    private void reset () {
        level = new HardViewportLevel(1, 1, new Vector(100, 10));

        Layout layout = new ContiguousLayout();
        layout.add(new BoundingBox(100, 100, 100, 50));
        layout.add(new BoundingBox(150, 50, 100, 50));
        layout.add(new BoundingBox(125, 150, 50, 100));
        layout.add(new BoundingBox(175, 175, 125, 25));
        layout.add(new BoundingBox(175, 225, 125, 25));

        level.add(layout, 0); // TODO: prioritize collisions with player
        level.add(new Block(new Vector(0, GameScreen.HEIGHT - 50), new Vector(GameScreen.WIDTH, 50)), 0);
    }

    @Override
    public void activate (Event event) {
        switch (event) {
            case reset:
                reset();
                break;
            case playerDeath:
                break;
            case playerJump:
                break;
        }
    }

    @Override
    public void step (EnumSet<Control> keys) {
        level.step(keys);
    }

    @Override
    public Level underlyingLevel () {
        return level;
    }

    @Override
    public void draw (Graphics2D g) {
        Drawable.draw(g, level.entities());
    }
}

// maybe the layout should just be for the drawing phase
// but it will have a method
// blocks()
// and that is what is added to the quadtree
// but it is drawn separately
// yes. this is good.