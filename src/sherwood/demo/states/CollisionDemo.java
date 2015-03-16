package sherwood.demo.states;

import sherwood.demo.physics.Collider;
import sherwood.demo.physics.Entity;
import sherwood.demo.physics.Stepper;
import sherwood.demo.physics.Vector;
import sherwood.demo.entities.player.Player;
import sherwood.demo.states.graphics.BoxArtist;
import sherwood.demo.structures.CollisionFactory;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;
import sherwood.screenStates.ScreenState;

import java.awt.Graphics2D;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class CollisionDemo extends ScreenState {

    private Set<Entity> entities;
    private CollisionFactory factory;

    public CollisionDemo () {
        entities = new HashSet<>();
        entities.add(new Player(new Vector(100, 200)));
        entities.add(new Player(new Vector(400, 100)));
        entities.add(new Player(new Vector(37, 57)));
        factory = new CollisionFactory();
    }


    @Override
    public void draw (Graphics2D g) {
        BoxArtist artist = new BoxArtist(g);
        entities.forEach(artist::draw);
        factory.draw(g);
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
