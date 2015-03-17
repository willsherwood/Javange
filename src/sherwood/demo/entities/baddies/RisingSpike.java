package sherwood.demo.entities.baddies;

import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Direction;

import java.awt.Polygon;

public class RisingSpike extends Spike {

    private Spike spike;
    private double showing = 16;

    public RisingSpike (BoundingBox bounds) {
        super(bounds, Direction.UP);
        this.spike = new Spike(bounds, Direction.UP);
    }

    @Override
    public Polygon poly () {
        throw new UnsupportedOperationException();
    }
}
