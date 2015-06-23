package sherwood.demo.game.structures.graph;

import sherwood.demo.game.structures.collisions.UnorderedPair;

import java.util.Set;

public interface Graph<T> {

    Set<T> vertices();

    Set<UnorderedPair<T>> edges();

    /**
     * @return all edges connected to vertex vertex
     */
    Set<T> verticesConnectedTo (T vertex);

    void connect (T a, T b);

    /**
     * remove this vertex from the graph
     * and also remove all edges with this vertex in it
     */
    void remove(T vertex);

    /**
     * add a single node
     */
    void connect (T solidBounds);
}
