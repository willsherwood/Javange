package sherwood.demo.game.structures.levels;

import sherwood.demo.game.entities.*;
import sherwood.demo.game.entities.player.Player;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;
import sherwood.demo.game.states.DepthEntity;
import sherwood.demo.game.structures.collisions.CollisionFactory;
import sherwood.demo.game.structures.levels.event.Event;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;

import java.util.EnumSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

// TODO: make a low level implementation of Level and have HardViewportLevel and
//       ScrollingViewportLevel act as a decorator to that

/**
 * A level with a scrolling X viewport for the player
 * but a hard Y screen switch
 */
public class ScrollingViewportLevel extends LevelState implements Level {

    private Queue<DepthEntity> entities;
    private Player player;
    private CollisionFactory factory;

    /**
     * constructs a level with rows by columns full screens
     */
    public ScrollingViewportLevel (int rows, int columns, Vector playerStart) {
        entities = new PriorityQueue<>();
        factory = new CollisionFactory(new Vector(rows * GameScreen.WIDTH, columns * GameScreen.HEIGHT));
        player = new Player(playerStart);
        add(player, 999);
        add(new Controller(), 0);
    }

    @Override
    public void add (Entity entity, int depth) {
        entities.add(new DepthEntity(entity, depth));
    }

    @Override
    public PriorityQueue<DepthEntity> entities () {
        PriorityQueue<DepthEntity> out = new PriorityQueue<>();
        int r = player.bounds().position().xc() - GameScreen.WIDTH / 2;
        if (r < 0)
            r = 0;
        int c = player.bounds().position().yc() / GameScreen.HEIGHT;
        BoundingBox viewport = new BoundingBox(new Vector(r, c * GameScreen.HEIGHT), new Vector(GameScreen.WIDTH, GameScreen.HEIGHT));
        out.addAll(entities.parallelStream()
                .filter(a -> a.entity() instanceof Drawable)
                .filter(a -> a.entity().bounds().intersects(viewport))
                .collect(Collectors.toList()));
        out.forEach(a -> a.drawingBounds(a.entity().bounds().over(viewport.position().negate())));
        return out;
    }

    /**
     *  running time: o(n) use sparingly
     */
    @Override
    public void remove (Entity entity) {
        entities.removeAll(entities.parallelStream().filter(a->a.entity().equals(entity)).collect(Collectors.toSet()));
    }

    @Override
    public void activate (Event event) {

    }

    @Override
    public void step (EnumSet<Control> keys) {
        entities.stream().filter(a -> a.entity() instanceof Stepper).forEach(a -> ((Stepper) a.entity()).step(keys));
        factory.collisions(entities.stream().map(DepthEntity::entity).collect(Collectors.toSet())).forEach(a -> {
            ((Collider) a.first()).collide(a.second());
            if (a.second() instanceof Collider)
                ((Collider) a.second()).collide(a.first());
        });
    }

    @Override
    public Player player () {
        return player;
    }

    @Override
    public Level underlyingLevel () {
        return this;
    }
}
