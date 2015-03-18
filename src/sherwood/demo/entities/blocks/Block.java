package sherwood.demo.entities.blocks;

import sherwood.demo.entities.Drawable;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Vector;

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
