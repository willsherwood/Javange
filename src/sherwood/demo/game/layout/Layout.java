package sherwood.demo.game.layout;

import sherwood.demo.game.entities.Collider;
import sherwood.demo.game.entities.Drawable;
import sherwood.demo.game.entities.Entity;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;

public interface Layout extends Entity, Collider, Drawable {

    void add(BoundingBox solidBounds);

    /**
     * removes all objects that include point position
     * @param position
     */
    void remove(Vector position);
}
