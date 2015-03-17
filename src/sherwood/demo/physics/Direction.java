package sherwood.demo.physics;

public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int x, y;

    Direction (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x () {
        return x;
    }

    public int y () {
        return y;
    }

    public boolean vertical () {
        return this == UP || this == DOWN;
    }
}
