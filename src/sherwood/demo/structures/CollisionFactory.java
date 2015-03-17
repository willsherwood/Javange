package sherwood.demo.structures;

import sherwood.demo.entities.Entity;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Vector;
import sherwood.demo.structures.collisions.CollisionTree;
import sherwood.gameScreen.GameScreen;

import java.awt.Graphics2D;
import java.util.Set;

public class CollisionFactory {

    private QuadTree tree;

    public CollisionFactory () {
        tree = new CollisionTree(new BoundingBox(new Vector(0, 0), new Vector(GameScreen.WIDTH, GameScreen.HEIGHT)));
    }

    public Set<UnorderedPair<Entity>> collisions (Set<Entity> entities) {
        tree.wipe();
        entities.stream().forEach(tree::insert);
        return tree.collidingPairs();
    }

    public void draw (Graphics2D g) {
        ((CollisionTree) tree).draw(g);
    }
}
