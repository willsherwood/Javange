package sherwood.demo.structures;

public class Pair<A, B> {
    public final A a;
    public final B b;

    public Pair (A a, B b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if (!a.equals(pair.a)) return false;
        if (!b.equals(pair.b)) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = a.hashCode();
        result = 31 * result + b.hashCode();
        return result;
    }
}
