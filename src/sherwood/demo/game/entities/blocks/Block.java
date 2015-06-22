package sherwood.demo.game.entities.blocks;

import sherwood.demo.game.entities.Drawable;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;

import java.awt.Color;
import java.awt.Graphics2D;

public class Block implements Drawable {

    private final BoundingBox bounds;

    public Block(Vector position, Vector size) {
        bounds = new BoundingBox(position, size);
    }

    @Override
    public BoundingBox bounds() {
        return bounds;
    }

    @Override
    public void draw (Graphics2D g, Vector position) {
        // for now lets redo this to just draw with lines and stuff
        g.setColor(Color.WHITE);
        g.fillRect(bounds.position().xc(), bounds.position().yc(), (int) bounds().width(), (int) bounds.height());
        g.setColor(Color.BLACK);
        g.drawRect(bounds.position().xc(), bounds.position().yc(), (int) bounds().width(), (int) bounds.height());
    }
}
