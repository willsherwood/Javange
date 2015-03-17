package sherwood.demo.states;

import sherwood.demo.entities.Entity;

public class DepthEntity implements Comparable<DepthEntity> {

    private int depth;
    private Entity entity;

    public DepthEntity (Entity entity, int depth) {
        this.entity = entity;
        this.depth = depth;
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

    @Override
    public int compareTo (DepthEntity o) {
        return depth - o.depth;
    }
}
