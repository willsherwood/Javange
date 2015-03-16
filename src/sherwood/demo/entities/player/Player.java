package sherwood.demo.entities.player;

import sherwood.demo.physics.*;
import sherwood.inputs.keyboard.control.Control;

import java.awt.Color;
import java.util.EnumSet;

public class Player implements Entity, Collider, Stepper {

    private static final Vector size = new Vector(5, 5);
    private Color color;

    private BoundingBox bounds;

    public Player (Vector start) {
        bounds = new BoundingBox(start, size);
        this.color = Color.WHITE;
    }

    @Override
    public BoundingBox bounds () {
        return bounds;
    }

    @Override
    public void collide (Entity entity) {
        this.color = Color.RED;
    }

    @Override
    public void step (EnumSet<Control> keys) {
        if (keys.contains(Control.LEFT))
            bounds = bounds.over(new Vector(-4, 0));
        if (keys.contains(Control.RIGHT))
            bounds = bounds.over(new Vector(4, 0));
        if (keys.contains(Control.UP))
            bounds = bounds.over(new Vector(0, -4));
        if (keys.contains(Control.DOWN))
            bounds = bounds.over(new Vector(0, 4));
    }

    public Color color () {
        return color;
    }
}
