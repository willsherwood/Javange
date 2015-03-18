package sherwood.demo.entities;

import sherwood.demo.physics.Vector;

import java.awt.Graphics2D;

public interface Drawable extends Entity {
    /**
     * draws this entity to the screen using the supplied graphics at the position on screen position
     */
    void draw (Graphics2D g, Vector position);
}
