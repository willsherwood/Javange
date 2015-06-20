package sherwood.demo.game.structures.levels;

import sherwood.demo.game.entities.*;
import sherwood.demo.game.entities.blocks.Block;
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

public class HardViewportLevel extends LevelState implements Level {

    private Queue<DepthEntity> entities;
    private Player player;
    private CollisionFactory factory;

    /**
     * constructs a level with rows by columns full screens
     */
    public HardViewportLevel(int rows, int columns, Vector playerStart) {
        entities = new PriorityQueue<>();
        factory = new CollisionFactory(new Vector(rows * GameScreen.WIDTH, columns * GameScreen.HEIGHT));
        player = new Player(playerStart);
        add(player, 999);
        add(new Controller(), 0);
    }

    @Override
    public void add(Entity entity, int depth) {
        entities.add(new DepthEntity(entity, depth));
    }

    @Override
    public PriorityQueue<DepthEntity> entities() {
        PriorityQueue<DepthEntity> out = new PriorityQueue<>();
        int r = player.bounds().position().xc() / GameScreen.WIDTH;
        int c = player.bounds().position().yc() / GameScreen.HEIGHT;
        BoundingBox viewport = new BoundingBox(new Vector(r * GameScreen.WIDTH, c * GameScreen.HEIGHT), new Vector(GameScreen.WIDTH, GameScreen.HEIGHT));
        out.addAll(entities.parallelStream()
                .filter(a -> a.entity() instanceof Drawable)
                .filter(a -> a.entity().bounds().intersects(viewport))
                .collect(Collectors.toList()));
        out.forEach(a -> a.drawingBounds(a.entity().bounds().over(viewport.position().negate())));
        out.add(new DepthEntity(factory, 998));
        return out;
    }

    /**
     * running time: o(n) use sparingly
     */
    @Override
    public void remove(Entity entity) {
        entities.removeAll(entities.parallelStream().filter(a -> a.entity().equals(entity)).collect(Collectors.toSet()));
    }

    @Override
    public void activate (Event event) {

    }

    @Override
    public void step(EnumSet<Control> keys) {
        super.step(keys);
        //TODO: parallel stream?
        entities.stream().filter(a -> a.entity() instanceof Stepper).forEach(a -> ((Stepper) a.entity()).step(keys));

        //TODO: do collisions by depth, so race conditions aren't met with collisions if the object that would collide is moved by a block on top of it
        factory.collisions(entities
                .stream()
                .map(DepthEntity::entity)
                .filter(a -> !(a instanceof Effect))
                .collect(Collectors.toSet()))
                .stream()
                .sorted((a,b) -> (a.first() instanceof Block || a.second() instanceof Block) ? -1 : 0)
                .forEach(a -> {
                    ((Collider) a.first()).collide(a.second());
                    if (a.second() instanceof Collider)
                        ((Collider) a.second()).collide(a.first());
                });
    }

    @Override
    public Player player() {
        return player;
    }
}
