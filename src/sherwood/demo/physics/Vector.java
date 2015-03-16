package sherwood.demo.physics;

public class Vector {

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
}
