package sherwood.demo.states;

import sherwood.demo.entities.Drawable;
import sherwood.demo.entities.Entity;
import sherwood.demo.entities.baddies.MovingSpike;
import sherwood.demo.entities.baddies.Spike;
import sherwood.demo.entities.blocks.Block;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Direction;
import sherwood.demo.physics.Vector;
import sherwood.demo.structures.levels.HardViewportLevel;
import sherwood.demo.structures.levels.Level;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;
import sherwood.screenStates.ScreenState;

import java.awt.Graphics2D;
import java.util.EnumSet;

public class RandomlyGeneratedLevel extends ScreenState {

    private Level level;

    public RandomlyGeneratedLevel () {
        reset();
    }

    public void reset () {
        level = new HardViewportLevel(2, 2, new Vector(64, 64));
        for (int x = 0; x < GameScreen.WIDTH * 2; x += 32) {
            if (Math.random() > .5) {
                bigBlock(x);
                x += 32;
            } else if (Math.random() > .5) {
                smallBlock(x);
            } else if (Math.random() > .5) {
                blockWithGap(x);
                x += 64;
            } else platform(x);
        }
    }

    private void platform (int x) {
        Entity block = new Block(new Vector(x, GameScreen.HEIGHT - 32), new Vector(32, 12));
        if (Math.random() > .7) {
            Entity spike = new Spike(new BoundingBox(new Vector(x, GameScreen.HEIGHT - 64), new Vector(32, 32)), Direction.UP);
            level.add(spike, -999);
        }
        level.add(block, 0);
    }

    private void blockWithGap (int x) {
        smallBlock(x);

    }

    private void smallBlock (int x) {
        Entity block = new Block(new Vector(x, GameScreen.HEIGHT - 32), new Vector(32, 32));
        if (Math.random() > .7) {
            Entity spike = new Spike(new BoundingBox(new Vector(x, GameScreen.HEIGHT - 64), new Vector(32, 32)), Direction.UP);
            level.add(spike, -999);
        }
        level.add(block, 0);
    }

    private void bigBlock (int x) {
        Entity block = new Block(new Vector(x, GameScreen.HEIGHT - 64), new Vector(64, 64));
        if (Math.random() > .5) {
            Entity movingSpike = new MovingSpike(new BoundingBox(new Vector(x, GameScreen.HEIGHT - 64 - 32), new Vector(32, 32)), new Vector(0, Math.random() * .8 + .2));
            level.add(movingSpike, -999);
        }
        level.add(block, 0);
    }

    @Override
    public void draw (Graphics2D g) {
        level.entities().forEach(a -> ((Drawable) a.entity()).draw(g));
    }

    @Override
    public void step (EnumSet<Control> keys) {
        level.step(keys);
    }
}
