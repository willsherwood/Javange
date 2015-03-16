package sherwood.demo.entities;

import sherwood.demo.physics.Vector;

public interface Collider extends Entity {
    /**
     * collide with this entity
     */
    void collide(Entity entity);
}
