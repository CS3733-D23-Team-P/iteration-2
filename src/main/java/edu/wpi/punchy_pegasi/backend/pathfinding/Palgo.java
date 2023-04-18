package edu.wpi.punchy_pegasi.backend.pathfinding;

import edu.wpi.punchy_pegasi.schema.INode;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class Palgo<K, T extends INode> {
    private final Graph<K, T> graph;
    private final IHeuristic<T> nextNodeScorer;
    private final IHeuristic<T> targetScorer;
    private final PathfindingSingleton pathfindingSingleton;

    public Palgo(Graph<K, T> graph, IHeuristic<T> nextNodeScorer, IHeuristic<T> targetScorer, PathfindingSingleton pathfindingSingleton) {
        this.graph = graph;
        this.nextNodeScorer = nextNodeScorer;
        this.targetScorer = targetScorer;
        this.pathfindingSingleton = pathfindingSingleton;
    }

//    public void setPathfinder(IPathFind<T> pathfinder) {
//        this.pathfinder = pathfinder;
//    }

    public List<T> findPath(T from, T to) throws IllegalStateException {
        IPathFind<T> pathfinder = pathfindingSingleton.getAlgorithm();
        return pathfinder.findPath(from, to);
    }
}
