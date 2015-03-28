package sherwood.demo.structures.levels;

import sherwood.demo.entities.Collider;
import sherwood.demo.entities.Drawable;
import sherwood.demo.entities.Entity;
import sherwood.demo.entities.Stepper;
import sherwood.demo.entities.player.Player;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Vector;
import sherwood.demo.states.DepthEntity;
import sherwood.demo.structures.collisions.CollisionFactory;
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
public class ScrollingViewportLevel implements Level {

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

    @Override
    public void step (EnumSet<Control> keys) {
        entities.stream().filter(a -> a.entity() instanceof Stepper).forEach(a -> ((Stepper) a.entity()).step(keys));
        factory.collisions(entities.stream().map(DepthEntity::entity).collect(Collectors.toSet())).forEach(a -> {
            ((Collider) a.first()).collide(a.second());
            if (a.second() instanceof Collider)
                ((Collider) a.second()).collide(a.first());
        });
    }
}