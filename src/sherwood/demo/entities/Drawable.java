package sherwood.demo.entities;

import sherwood.demo.graphics.SpriteBox;
import sherwood.demo.physics.Vector;

import java.awt.Graphics2D;

public interface Drawable extends Entity {
    /**
     * draws this entity to the screen using the supplied graphics at the position on screen position
     * by default it will draw the sprite of the classname at the position, tiled by 32.
     */
    default void draw (Graphics2D g, Vector position) {
        tile(g, position, this, this.getClass().getSimpleName());
    }

    /**
     * tile the given entity's sprite given by the class name (will access file res/img/className.png)
     */
    static void tile (Graphics2D g, Vector position, Entity entity, String className) {
        for (int y = 0; y < entity.bounds().height() / 32; y++)
            for (int x = 0; x < entity.bounds().width() / 32; x++)
                g.drawImage(SpriteBox.instance().sprite("res/img/" + className + ".png"),
                        position.over(new Vector(x * 32, 0)).xc(), position.over(new Vector(0, y * 32)).yc(), null);
    }
}
