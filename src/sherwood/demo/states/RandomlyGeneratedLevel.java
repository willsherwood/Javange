package sherwood.demo.states;

import sherwood.demo.entities.Drawable;
import sherwood.demo.entities.Entity;
import sherwood.demo.entities.Triggered;
import sherwood.demo.entities.baddies.spike.MovingSpike;
import sherwood.demo.entities.baddies.spike.Spike;
import sherwood.demo.entities.baddies.triggered.TriggeredSpike;
import sherwood.demo.entities.blocks.Block;
import sherwood.demo.entities.trigger.CollisionTrigger;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Direction;
import sherwood.demo.physics.Vector;
import sherwood.demo.structures.levels.HardViewportLevel;
import sherwood.demo.structures.levels.Level;
import sherwood.demo.structures.levels.LevelState;
import sherwood.demo.structures.levels.ScrollingViewportLevel;
import sherwood.demo.structures.levels.event.Event;
import sherwood.gameScreen.FPSUpdateAlgorithm;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;
import sherwood.inputs.keyboard.control.MixedKeyboardInput;

import java.awt.*;
import java.util.EnumSet;
import java.util.Queue;

public class RandomlyGeneratedLevel extends LevelState {

    private Level level;
    private boolean hard;

    public RandomlyGeneratedLevel(boolean hard) {
        this.hard = hard;
        reset();
    }

    @Override
    public void init() {
        GameScreen.get().requestKeyInputMechanism(new MixedKeyboardInput(EnumSet.of(Control.LEFT, Control.RIGHT, Control.UP, Control.DOWN, Control.A)));
        GameScreen.get().requestUpdateAlgorithm(new FPSUpdateAlgorithm(60));
        // Sound.PATHETIQUE.play();
    }

    public void reset() {
        if (hard)
            level = new HardViewportLevel(2, 2, new Vector(64, 64));
        else
            level = new ScrollingViewportLevel(2, 2, new Vector(64, 64));
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

    private void platform(int x) {
        Entity block = new Block(new Vector(x, GameScreen.HEIGHT - 32), new Vector(32, 12));
        if (Math.random() > .7) {
            Entity spike = new Spike(new BoundingBox(new Vector(x, GameScreen.HEIGHT - 64), new Vector(32, 32)), Direction.UP);
            level.add(spike, -999);
        }
        level.add(block, 0);
    }

    private void blockWithGap(int x) {
        smallBlock(x);

    }

    private void smallBlock(int x) {
        Entity block = new Block(new Vector(x, GameScreen.HEIGHT - 32), new Vector(32, 32));
        if (Math.random() > .99) {
            Entity spike = new Spike(new BoundingBox(new Vector(x, GameScreen.HEIGHT - 64), new Vector(32, 32)), Direction.UP);
            level.add(spike, -999);
        } else {
            Triggered spike = new TriggeredSpike(new BoundingBox(
                    new Vector(x, GameScreen.HEIGHT - 64), new Vector(32, 32)), Vector.ZERO);
            Entity trigger = new CollisionTrigger(new BoundingBox(x - 4, 0, 40, GameScreen.HEIGHT), spike);
            level.add(spike, 0);
            level.add(trigger, 0);
        }
        level.add(block, 0);
    }

    private void bigBlock(int x) {
        Entity block = new Block(new Vector(x, GameScreen.HEIGHT - 64), new Vector(64, 64));
        if (Math.random() > .5) {
            Entity movingSpike = new MovingSpike(new BoundingBox(new Vector(x, GameScreen.HEIGHT - 64 - 32), new Vector(32, 32)), new Vector(0, Math.random() * .8 + .2));
            level.add(movingSpike, -999);
        }
        level.add(block, 0);
    }

    @Override
    public void draw(Graphics2D g) {
        Queue<DepthEntity> drawingQueue = level.entities();
        while (!drawingQueue.isEmpty()) {
            DepthEntity entity = drawingQueue.poll();
            ((Drawable) entity.entity()).draw(g, entity.drawingBounds().position());
        }
    }

    @Override
    public void step(EnumSet<Control> keys) {
        level.step(keys);
    }

    @Override
    public void activate(Event event) {
        if (event == Event.playerDeath) {
            reset();
        }
    }
}
