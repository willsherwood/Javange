package sherwood.demo.entities.baddies;

import sherwood.demo.entities.Entity;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Vector;

public abstract class Baddie implements Entity {

    private BoundingBox bounds;

    public Baddie(BoundingBox bounds) {
        this.bounds = bounds;
    }

    @Override
    public BoundingBox bounds () {
        return bounds;
    }

    public void moveTo(Vector here) {
        bounds = new BoundingBox(here, bounds.size());
    }
}
