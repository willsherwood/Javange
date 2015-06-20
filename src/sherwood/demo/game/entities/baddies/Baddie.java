package sherwood.demo.game.entities.baddies;

import sherwood.demo.game.entities.Drawable;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;

public abstract class Baddie implements Drawable {

    private BoundingBox bounds;

    public Baddie (BoundingBox bounds) {
        this.bounds = bounds;
    }

    @Override
    public BoundingBox bounds () {
        return bounds;
    }

    public void moveTo (Vector here) {
        bounds = new BoundingBox(here, bounds.size());
    }
}
