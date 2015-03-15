package sherwood.demo.physics;

@FunctionalInterface
public interface Collider {

    /**
     * an object that should trigger an effect when it collides with something else
     */
    void collide(Entity entity);
}
