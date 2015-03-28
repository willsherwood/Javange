package sherwood.demo.structures.levels;

import sherwood.demo.entities.*;
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

public class HardViewportLevel implements Level {

    private Queue<DepthEntity> entities;
    private Player player;
    private CollisionFactory factory;

    /**
     * constructs a level with rows by columns full screens
     */
    public HardViewportLevel (int rows, int columns, Vector playerStart) {
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
        int r = player.bounds().position().xc() / GameScreen.WIDTH;
        int c = player.bounds().position().yc() / GameScreen.HEIGHT;
        BoundingBox viewport = new BoundingBox(new Vector(r*GameScreen.WIDTH, c*GameScreen.HEIGHT), new Vector(GameScreen.WIDTH, GameScreen.HEIGHT));
        out.addAll(entities.parallelStream()
                .filter(a -> a.entity() instanceof Drawable)
                .filter(a -> a.entity().bounds().intersects(viewport))
                .collect(Collectors.toList()));
        out.forEach(a->a.drawingBounds(a.entity().bounds().over(viewport.position().negate())));
        //out.add(new DepthEntity(factory, 998));
        return out;
    }

    /**
     *  running time: o(n) use sparingly
     */
    @Override
    public void remove (Entity entity) {
        entities.removeAll(entities.parallelStream().filter(a -> a.entity().equals(entity)).collect(Collectors.toSet()));
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
}
