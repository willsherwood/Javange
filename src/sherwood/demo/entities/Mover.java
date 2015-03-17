package sherwood.demo.entities;

import sherwood.demo.physics.Vector;

public interface Mover extends Entity {
    /**
     * returns the current velocity of the mover
     */
    Vector velocity ();
}
