package sherwood.demo.structures.collisions;

import sherwood.demo.physics.Entity;
import sherwood.demo.structures.UnorderedPair;

import java.util.*;

/**
 *  data structure quad-tree of axis aligned bounding boxes
 */
public class CollisionTree implements QuadTree {


    @Override
    public boolean insert (Entity entity) {
        return false;
    }

    @Override
    public Set<UnorderedPair<Entity>> collidingPairs () {
        return null;
    }
}
