package sherwood.demo.physics;

public interface Collider extends Entity {
    /**
     * an object that should trigger an effect when it collides with something else
     */
    void collide (Entity entity);
}
