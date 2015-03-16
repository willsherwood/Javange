package sherwood.demo.physics;

public class Vector {

    public static final Vector ZERO = new Vector(0, 0);

    private final double x;
    private final double y;

    public Vector (double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x-component of this vector
     */
    public double x () {
        return x;
    }

    /**
     * @return the y-component of this vector
     */
    public double y () {
        return y;
    }

    /**
     * @return this vector plus v
     */
    public Vector over (Vector v) {
        return new Vector(x + v.x, y + v.y);
    }

    /**
     * @return the int-casted x-component of this vector. xc stands for 'x casted' (to int)
     */
    public int xc () {
        return (int) x;
    }

    /**
     * @return the int-casted y-component of this vector. yc stands for 'y casted' (to int)
     */
    public int yc () {
        return (int) y;
    }

    /**
     * @return a new vector with delta-x dx
     */
    public Vector dx(double dx) {
        return new Vector(x + dx, y);
    }

    /**
     * @return a new vector with delta-y dy
     */
    public Vector dy(double dy) {
        return new Vector(x, y + dy);
    }

    public Vector negate () {
        return new Vector(-x, -y);
    }

    /**
     * @return a vector with the x zeroed (zeroed-x)
     */
    public Vector zx() {
        return new Vector(0, y);
    }

    /**
     * @return a vector with the y zeroed (zeroed-y)
     */
    public Vector zy() {
        return new Vector(x, 0);
    }

    /**
     * @return a vector with its x set to x (set-x)
     */
    public Vector sx(double x) {
        return new Vector(x, y);
    }

    /**
     * @return a vector with its x set to x (set-x)
     */
    public Vector sy(double y) {
        return new Vector(x, y);
    }
}
