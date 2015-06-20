package sherwood.demo.game.entities;

import sherwood.demo.game.physics.Vector;

public interface Mover extends Entity {
    /**
     * returns the current velocity of the mover
     */
    Vector velocity ();
}
