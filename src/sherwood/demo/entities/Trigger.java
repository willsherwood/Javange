package sherwood.demo.entities;

import sherwood.demo.entities.player.Player;

public interface Trigger extends Collider {
    /**
     * should be called when the player collides with this
     */
    void trigger();

    @Override
    default void collide (Entity entity) {
        if (entity instanceof Player)
            trigger();
    }
}
