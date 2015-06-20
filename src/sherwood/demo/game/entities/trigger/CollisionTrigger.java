package sherwood.demo.game.entities.trigger;

import sherwood.demo.game.entities.Collider;
import sherwood.demo.game.entities.Entity;
import sherwood.demo.game.entities.Triggered;
import sherwood.demo.game.entities.player.Player;
import sherwood.demo.game.physics.BoundingBox;

import java.util.Collection;
import java.util.Collections;

public class CollisionTrigger implements Collider {

    private final Collection<Triggered> things;
    private final BoundingBox position;
    private boolean clearing;

    public CollisionTrigger(BoundingBox position, Collection<Triggered> things) {
        this.position = position;
        this.things = things;
    }

    public CollisionTrigger(BoundingBox position, Triggered thing) {
        this(position, Collections.singleton(thing));
    }

    public CollisionTrigger(BoundingBox position, Collection<Triggered> things, boolean clearing) {
        this(position, things);
        this.clearing = clearing;
    }

    @Override
    public void collide(Entity entity) {
        if (entity instanceof Player) {
            things.forEach(Triggered::trigger);
            if (clearing)
                things.clear();
        }
    }

    @Override
    public BoundingBox bounds() {
        return position;
    }
}
