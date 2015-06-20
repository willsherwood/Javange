package sherwood.demo.game.entities;

import sherwood.demo.game.physics.BoundingBox;

public interface Entity {
    /**
     * returns the bounds of this entity
     */
    BoundingBox bounds ();
}
