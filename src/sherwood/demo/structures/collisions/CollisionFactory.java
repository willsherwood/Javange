package sherwood.demo.structures.collisions;

import sherwood.demo.entities.Drawable;
import sherwood.demo.entities.Entity;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Vector;
import sherwood.gameScreen.GameScreen;

import java.awt.Graphics2D;
import java.util.Set;

public class CollisionFactory implements Drawable {

    private QuadTree tree;

    public CollisionFactory () {
        tree = new CollisionTree(new BoundingBox(new Vector(0, 0), new Vector(GameScreen.WIDTH, GameScreen.HEIGHT)));
    }

    public CollisionFactory (Vector size) {
        tree = new CollisionTree(new BoundingBox(new Vector(0, 0), size));
    }

    public Set<UnorderedPair<Entity>> collisions (Set<Entity> entities) {
        tree.wipe();
        entities.stream().forEach(tree::insert);
        return tree.collidingPairs();
    }

    @Override
    public void draw (Graphics2D g, Vector position) {
        ((CollisionTree) tree).draw(g);
    }

    @Override
    public BoundingBox bounds () {
        return new BoundingBox(new Vector(0, 0), new Vector(GameScreen.WIDTH, GameScreen.HEIGHT));
    }
}
