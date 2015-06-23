package sherwood.demo.game.structures.graph;

import sherwood.demo.game.structures.collisions.UnorderedPair;

import java.util.*;

public class UUGraph<T> implements Graph<T> {

    private Map<T, Set<T>> graph;

    public UUGraph () {
        graph = new HashMap<>();
    }

    @Override
    public Set<T> vertices () {
        return graph.keySet();
    }

    @Override
    @HighRunningTime(complexity = HighRunningTime.RunningTime.LINEAR)
    public Set<UnorderedPair<T>> edges () {
        Set<UnorderedPair<T>> out = new HashSet<>();
        graph.entrySet().forEach(a -> a.getValue().forEach(b -> out.add(new UnorderedPair<>(a.getKey(), b))));
        return out;
    }

    @Override
    public Set<T> verticesConnectedTo (T vertex) {
        return graph.getOrDefault(vertex, Collections.emptySet());
    }

    @Override
    public void connect (T a, T b) {
        graph.putIfAbsent(a, new HashSet<>());
        graph.putIfAbsent(b, new HashSet<>());
        graph.get(a).add(b);
    }

    @Override
    @HighRunningTime(complexity = HighRunningTime.RunningTime.LINEAR)
    public void remove (T vertex) {
        graph.remove(vertex);
        graph.values().forEach(a -> a.remove(vertex));
    }

    @Override
    public void connect (T solidBounds) {
        graph.putIfAbsent(solidBounds, new HashSet<>());
    }
}
