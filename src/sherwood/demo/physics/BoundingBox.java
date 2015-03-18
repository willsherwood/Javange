package sherwood.demo.physics;

import java.awt.Rectangle;

/**
 * An immutable object representing an axis-aligned bounding box
 */
public class BoundingBox {

    private Vector position, size;

    public BoundingBox (double x, double y, double width, double height) {
        this(new Vector(x, y), new Vector(width, height));
    }

    public BoundingBox (Vector position, Vector size) {
        this.position = position;
        this.size = size;
    }

    public Vector position () {
        return position;
    }

    public Vector size () {
        return size;
    }

    public double x () {
        return position.x();
    }

    public double y () {
        return position.y();
    }

    public double width () {
        return size.x();
    }

    public double height () {
        return size.y();
    }

    public BoundingBox over (Vector vector) {
        return new BoundingBox(position.over(vector), size);
    }

    public boolean intersects (BoundingBox bounds) {
        Rectangle a = new Rectangle(position.xc(), position.yc(), size.xc(), size.yc());
        Rectangle b = new Rectangle(bounds.position.xc(), bounds.position.yc(), bounds.size.xc(), bounds.size.yc());
        return a.intersects(b);
    }

    public Rectangle rect () {
        return new Rectangle(position.xc(), position.yc(), size.xc(), size.yc());
    }

    public BoundingBox resize (double width, double height) {
        return new BoundingBox(position, new Vector(width, height));
    }
}
