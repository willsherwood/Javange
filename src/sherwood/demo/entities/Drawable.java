package sherwood.demo.entities;

import java.awt.Graphics2D;

public interface Drawable extends Entity {
    /**
     * draws this entity to the screen using the supplied graphics
     */
    void draw (Graphics2D g);
}
