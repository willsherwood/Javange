package sherwood.demo.entities.trigger;

import sherwood.demo.entities.Collider;
import sherwood.demo.entities.Entity;
import sherwood.demo.entities.Triggered;
import sherwood.demo.entities.player.Player;
import sherwood.demo.physics.BoundingBox;

import java.util.Collection;
import java.util.Collections;

public class CollisionTrigger implements Collider {

    private final Collection<Triggered> things;
    private final BoundingBox position;

    public CollisionTrigger(BoundingBox position, Collection<Triggered> things) {
        this.position = position;
        this.things = things;
    }

    public CollisionTrigger(BoundingBox position, Triggered thing) {
        this(position, Collections.singleton(thing));
    }

    @Override
    public void collide (Entity entity) {
        if (entity instanceof Player)
            things.forEach(Triggered::trigger);
    }

    @Override
    public BoundingBox bounds () {
        return position;
    }
}
