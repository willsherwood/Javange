package sherwood.demo.game.entities.blocks;

import sherwood.demo.game.entities.Drawable;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;

public class Block implements Drawable {

    private final BoundingBox bounds;

    public Block(Vector position, Vector size) {
        bounds = new BoundingBox(position, size);
    }

    @Override
    public BoundingBox bounds() {
        return bounds;
    }
}
