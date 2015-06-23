package sherwood.demo.game.entities.blocks;

import sherwood.demo.game.entities.Drawable;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;

//TODO: block has no extra properties. why not replace with a bounding box?
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
