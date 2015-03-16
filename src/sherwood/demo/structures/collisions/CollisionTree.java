package sherwood.demo.structures.collisions;

import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Collider;
import sherwood.demo.physics.Entity;
import sherwood.demo.states.graphics.BoxArtist;
import sherwood.demo.structures.QuadTree;
import sherwood.demo.structures.UnorderedPair;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * data structure quad-tree of axis aligned bounding boxes
 */
public class CollisionTree implements QuadTree {

    /**
     * How many entities this tree can store before it splits
     */
    public static final int BUCKET_CAPACITY = 12;

    /**
     * The minimum width that a tree will split into
     */
    public static final int MINIMUM_WIDTH = 8;

    private BoundingBox bounds;
    private Optional<QuadTree[]> children;
    private Set<Entity> entities;

    public CollisionTree (BoundingBox bounds) {
        this.bounds = bounds;
        entities = new HashSet<>(BUCKET_CAPACITY);
        children = Optional.empty();
    }

    @Override
    public boolean insert (Entity entity) {
        if (!bounds.intersects(entity.bounds()))
            return false;
        if (children.isPresent())
            return insertChildren(entity);
        if (entities.size() == BUCKET_CAPACITY && this.bounds.width() / 2 >= MINIMUM_WIDTH) {
            split();
            return insertChildren(entity);
        }
        return entities.add(entity);
    }


    @Override
    public Set<UnorderedPair<Entity>> collidingPairs () {
        Set<UnorderedPair<Entity>> collisions = new HashSet<>();
        Set<Entity> copy = new HashSet<>(entities);
        entities.stream().filter(a -> a instanceof Collider).forEach(a -> {
            copy.remove(a);
            copy.forEach(Q -> {
                if (Q.bounds().intersects(a.bounds()))
                    collisions.add(new UnorderedPair<>(Q, a));
            });
        });
        children.ifPresent(a -> {
            for (QuadTree kid : a)
                collisions.addAll(kid.collidingPairs());
        });
        return collisions;
    }

    /**
     * splits this collision tree into 4 separate quad trees.
     */
    private void split () {
        QuadTree[] kids = new QuadTree[4];
        children = Optional.of(kids);
        double width = bounds.width() / 2, height = bounds.height() / 2;
        kids[0] = new CollisionTree(new BoundingBox(bounds.x(), bounds.y(), width, height));
        kids[1] = new CollisionTree(new BoundingBox(bounds.x() + width, bounds.y(), width, height));
        kids[2] = new CollisionTree(new BoundingBox(bounds.x(), bounds.y() + height, width, height));
        kids[3] = new CollisionTree(new BoundingBox(bounds.x() + width, bounds.y() + height, width, height));
        for (QuadTree Q : kids)
            entities.forEach(Q::insert);
        entities.clear();
    }

    private boolean insertChildren (Entity entity) {
        boolean out = false;
        for (QuadTree tree : children.get())
            out |= tree.insert(entity);
        return out;
    }

    public void wipe() {
        children = Optional.empty();
        entities.clear();
    }

    public void draw (Graphics2D g) {
        Color t = g.getColor();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
        g.setColor(Color.GREEN);
        new BoxArtist(g, ArtStyle.NO_FILL).draw(() -> bounds);
        children.ifPresent(a -> {
            for (QuadTree Q : a)
                ((CollisionTree) Q).draw(g);
        });
        g.setColor(t);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
