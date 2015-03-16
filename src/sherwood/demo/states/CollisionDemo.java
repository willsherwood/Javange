package sherwood.demo.states;

import sherwood.demo.entities.Collider;
import sherwood.demo.entities.Entity;
import sherwood.demo.entities.Stepper;
import sherwood.demo.entities.blocks.Block;
import sherwood.demo.entities.player.Player;
import sherwood.demo.physics.Vector;
import sherwood.demo.states.graphics.BoxArtist;
import sherwood.demo.structures.CollisionFactory;
import sherwood.gameScreen.FPSUpdateAlgorithm;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;
import sherwood.inputs.keyboard.control.MixedKeyboardInput;
import sherwood.screenStates.ScreenState;

import java.awt.Graphics2D;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class CollisionDemo extends ScreenState {

    private Player player;
    private Set<Entity> entities;
    private CollisionFactory factory;

    public CollisionDemo () {
        entities = new HashSet<>();
        player = new Player(new Vector(100, 300));
        entities.add(player);
        for (int x = 0; x < GameScreen.WIDTH; x += 32)
            if (Math.random() > 0.4) {
                entities.add(new Block(new Vector(x, GameScreen.HEIGHT - 64), new Vector(64, 64)));
                x += 32;
            } else if (Math.random() > 0.6)
                entities.add(new Block(new Vector(x, GameScreen.HEIGHT - 32), new Vector(32, 32)));

        factory = new CollisionFactory();
    }

    @Override
    public void init () {
        EnumSet<Control> continuousKeys = EnumSet.of(Control.LEFT, Control.RIGHT, Control.UP, Control.DOWN);
        GameScreen.get().requestKeyInputMechanism(new MixedKeyboardInput(continuousKeys));
        GameScreen.get().requestUpdateAlgorithm(new FPSUpdateAlgorithm());
    }

    @Override
    public void draw (Graphics2D g) {
        BoxArtist artist = new BoxArtist(g);
        entities.forEach(artist::draw);
        factory.draw(g);
        g.drawString(player.bounds().position().toString(), 20, 40);
    }

    @Override
    public void step (EnumSet<Control> keys) {
        if (keys.contains(Control.START)) {
            entities.add(new Player(new Vector((int) (Math.random() * GameScreen.WIDTH), (int) (Math.random() * GameScreen.HEIGHT))));
        }
        if (keys.contains(Control.UP)) {
            for (int i = 0; i < 25; i++)
                entities.add(new Player(new Vector((int) (Math.random() * GameScreen.WIDTH), (int) (Math.random() * GameScreen.HEIGHT))));
        }
        entities.stream().filter(a -> a instanceof Stepper).forEach(b -> ((Stepper) b).step(keys));
        factory.collisions(entities).forEach(a -> {
            ((Collider) a.first()).collide(a.second());
            if (a.second() instanceof Collider)
                ((Collider) a.second()).collide(a.first());
        });
    }
}
