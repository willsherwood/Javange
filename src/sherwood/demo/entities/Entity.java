package sherwood.demo.entities;

import sherwood.demo.physics.BoundingBox;

public interface Entity {
    /**
     * returns the bounds of this entity
     */
    BoundingBox bounds ();
}
