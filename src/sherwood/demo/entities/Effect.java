package sherwood.demo.entities;

/**
 * an effect is an entity that will not show collision with anything
 * this means colliders will not check collisions with this and instances
 * of Effect will not be put into the collision tree
 */
public interface Effect extends Entity {
}
