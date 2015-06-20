package sherwood.demo.game.states;

import sherwood.demo.game.entities.Entity;
import sherwood.demo.game.physics.BoundingBox;

import java.util.Optional;

public class DepthEntity implements Comparable<DepthEntity> {

    private int depth;
    private Entity entity;
    private Optional<BoundingBox> drawingBounds;

    public DepthEntity (Entity entity, int depth) {
        this(entity, depth, Optional.empty());
    }

    public DepthEntity (Entity entity, int depth, Optional<BoundingBox> drawingBounds) {
        this.entity = entity;
        this.depth = depth;
        this.drawingBounds = drawingBounds;
    }

    public int depth () {
        return depth;
    }

    public void depth (int depth) {
        this.depth = depth;
    }

    public Entity entity () {
        return entity;
    }

    /**
     * @return the bounds to draw this image if they are specified, otherwise
     * the original bounds
     */
    public BoundingBox drawingBounds() {
        return drawingBounds.orElse(entity.bounds());
    }

    public void drawingBounds(BoundingBox boundingBox) {
        drawingBounds = Optional.of(boundingBox);
    }

    @Override
    public int compareTo (DepthEntity o) {
        return depth - o.depth;
    }
}
