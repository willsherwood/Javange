package sherwood.demo.states;

import sherwood.demo.entities.Collider;
import sherwood.demo.entities.Entity;
import sherwood.demo.entities.Stepper;
import sherwood.demo.entities.baddies.MovingBaddie;
import sherwood.demo.entities.baddies.MovingSpike;
import sherwood.demo.entities.baddies.Spike;
import sherwood.demo.entities.blocks.Block;
import sherwood.demo.entities.player.Player;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Direction;
import sherwood.demo.physics.Vector;
import sherwood.demo.states.graphics.FillArtist;
import sherwood.demo.states.graphics.PlayerArtist;
import sherwood.demo.structures.CollisionFactory;
import sherwood.gameScreen.FPSUpdateAlgorithm;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;
import sherwood.inputs.keyboard.control.MixedKeyboardInput;
import sherwood.screenStates.ScreenState;

import java.awt.*;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class CollisionDemo extends ScreenState {

    private Player player;
    private Set<Entity> entities;
    private Set<Spike> spikes;
    private Set<MovingSpike> movingSpikes;

    private CollisionFactory factory;

    public CollisionDemo() {
        entities = new HashSet<>();
        spikes = new HashSet<>();
        movingSpikes = new HashSet<>();
        reset();
    }

    public void reset() {
        spikes.clear();
        movingSpikes.clear();
        entities.clear();
        player = new Player(new Vector(100, 300));
        entities.add(player);
        for (int x = 0; x < GameScreen.WIDTH; x += 32)
            if (Math.random() > 0.4) {
                entities.add(new Block(new Vector(x, GameScreen.HEIGHT - 64), new Vector(64, 64)));
                x += 32;
                if (Math.random() > .5)
                    spikes.add(new Spike(new BoundingBox(new Vector(x, GameScreen.HEIGHT - 64 - 32), new Vector(32, 32)), Direction.UP));
            } else if (Math.random() > 0.6) {
                entities.add(new Block(new Vector(x, GameScreen.HEIGHT - 32), new Vector(32, 32)));
                movingSpikes.add(new MovingSpike(new BoundingBox(new Vector(x, GameScreen.HEIGHT - 64), new Vector(32, 32)), new Vector(0, -1)));

            }
        entities.add(new Block(new Vector(0, GameScreen.HEIGHT - 32), new Vector(32, 32)));
        entities.add(new Block(new Vector(0, GameScreen.HEIGHT - 128), new Vector(16, 96)));
        entities.add(new MovingBaddie(new BoundingBox(new Vector(600, GameScreen.HEIGHT - 100), new Vector(20, 20)), new Vector(-3.75, 0)));

        factory = new CollisionFactory();
    }

    @Override
    public void init() {
        EnumSet<Control> continuousKeys = EnumSet.of(Control.LEFT, Control.RIGHT, Control.UP, Control.DOWN, Control.A);
        GameScreen.get().requestKeyInputMechanism(new MixedKeyboardInput(continuousKeys));
        GameScreen.get().requestUpdateAlgorithm(new FPSUpdateAlgorithm());
    }

    @Override
    public void draw(Graphics2D g) {
        movingSpikes.forEach(a -> new MovingSpike.SpikeArtist().draw(a, g));
        g.setColor(Color.WHITE);
        FillArtist artist = new FillArtist();
        Spike.SpikeArtist spikeArist = new Spike.SpikeArtist();
        entities.forEach(a -> artist.draw(a, g));
        spikes.forEach(a -> spikeArist.draw(a, g));
        factory.draw(g);
        new PlayerArtist().draw(player, g);
        g.setColor(Color.WHITE);
        g.drawString(player.bounds().position().toString(), 20, 40);
        g.drawString(player.velocity().toString(), 20, 65);
    }

    @Override
    public void step(EnumSet<Control> keys) {
        if (keys.contains(Control.SELECT)) {
            // restart
            entities.clear();
            reset();
            return;
        }
        movingSpikes.forEach(a -> a.step(keys));
        entities.stream().filter(a -> a instanceof Stepper).forEach(b -> ((Stepper) b).step(keys));
        entities.addAll(spikes);
        entities.addAll(movingSpikes);
        factory.collisions(entities).forEach(a -> {
            ((Collider) a.first()).collide(a.second());
            if (a.second() instanceof Collider)
                ((Collider) a.second()).collide(a.first());
        });
        entities.removeAll(spikes);
        entities.removeAll(movingSpikes);
    }
}
