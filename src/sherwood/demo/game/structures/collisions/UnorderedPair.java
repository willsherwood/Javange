package sherwood.demo.game.structures.collisions;

public class UnorderedPair<T> {

    private final T a, b;

    public UnorderedPair (T a, T b) {
        this.a = a;
        this.b = b;
    }

    public T first () {
        return a;
    }

    public T second () {
        return b;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnorderedPair that = (UnorderedPair) o;
        return that.a.equals(a) && that.b.equals(b) || that.a.equals(b) && that.b.equals(a);
    }

    @Override
    public int hashCode () {
        return a.hashCode() ^ b.hashCode();
        // we can return the xor of the hashes because order doesn't matter
    }
}
