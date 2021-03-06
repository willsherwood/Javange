package sherwood.demo.game.structures.collisions;

import sherwood.demo.game.entities.Entity;

import java.util.Set;

public interface QuadTree extends Entity {
    /**
     * attempts to insert an entity into this quad tree
     *
     * @param entity the entity to insert into the tree
     * @return whether or not the entity was successfully added
     */
    boolean insert (Entity entity);

    /**
     * returns all pairs of objects colliding. Because they are
     * in no specific order, (a, b) will be returned but not (b, a).
     */
    Set<UnorderedPair<Entity>> collidingPairs ();

    /**
     * clear the tree
     */
    void wipe ();
}
