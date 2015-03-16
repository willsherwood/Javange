package sherwood.demo.states.graphics;

import sherwood.demo.entities.Entity;

import java.awt.Graphics2D;

@FunctionalInterface
public interface Artist<T extends Entity> {

    /**
     * draws the entity t with graphics g
     */
    public void draw(T t, Graphics2D g);
}
