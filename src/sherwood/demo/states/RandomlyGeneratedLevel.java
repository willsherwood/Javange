package sherwood.demo.states;

import sherwood.demo.entities.Collider;
import sherwood.demo.entities.Drawable;
import sherwood.demo.entities.Entity;
import sherwood.demo.entities.Stepper;
import sherwood.demo.entities.baddies.MovingSpike;
import sherwood.demo.entities.baddies.Spike;
import sherwood.demo.entities.blocks.Block;
import sherwood.demo.entities.player.Player;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Direction;
import sherwood.demo.physics.Vector;
import sherwood.demo.structures.CollisionFactory;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;
import sherwood.screenStates.ScreenState;

import java.awt.Graphics2D;
import java.util.EnumSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class RandomlyGeneratedLevel extends ScreenState {

    private Queue<DepthEntity> entities;
    private CollisionFactory factory;
    private Player player;

    public RandomlyGeneratedLevel () {
        entities = new PriorityQueue<>();
        factory = new CollisionFactory();
        reset();
    }

    public void reset () {
        player = new Player(new Vector(64, 64));
        entities.clear();
        entities.add(new DepthEntity(player, -999));
        for (int x = 0; x < GameScreen.WIDTH; x += 32) {
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
            entities.add(new DepthEntity(spike, 999));
        }
        entities.add(new DepthEntity(block, 0));
    }

    private void blockWithGap (int x) {
        smallBlock(x);

    }

    private void smallBlock (int x) {
        Entity block = new Block(new Vector(x, GameScreen.HEIGHT - 32), new Vector(32, 32));
        if (Math.random() > .7) {
            Entity spike = new Spike(new BoundingBox(new Vector(x, GameScreen.HEIGHT - 64), new Vector(32, 32)), Direction.UP);
            entities.add(new DepthEntity(spike, 999));
        }
        entities.add(new DepthEntity(block, 0));
    }

    private void bigBlock (int x) {
        Entity block = new Block(new Vector(x, GameScreen.HEIGHT - 64), new Vector(64, 64));
        if (Math.random() > .5) {
            Entity movingSpike = new MovingSpike(new BoundingBox(new Vector(x, GameScreen.HEIGHT - 64 - 32), new Vector(32, 32)), new Vector(0, Math.random() * .8 + .2));
            entities.add(new DepthEntity(movingSpike, 999));
        }
        entities.add(new DepthEntity(block, 0));
    }

    @Override
    public void draw (Graphics2D g) {
        entities.stream().filter(a -> a.entity() instanceof Drawable).forEach(a -> ((Drawable) a.entity()).draw(g));
    }

    @Override
    public void step (EnumSet<Control> keys) {
        entities.stream().filter(a -> a.entity() instanceof Stepper).forEach(a -> ((Stepper) a.entity()).step(keys));
        factory.collisions(entities.stream().map(a -> a.entity()).collect(Collectors.toSet())).forEach(a -> {
            ((Collider) a.first()).collide(a.second());
            if (a.second() instanceof Collider)
                ((Collider) a.second()).collide(a.first());
        });
    }
}
