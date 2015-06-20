package sherwood.demo.game.entities;

public interface Collider extends Entity {
    /**
     * collide with this entity
     */
    void collide (Entity entity);
}
