import java.util.*;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Ruiyang Qin
 * @userid rqin37
 * @GTID 903146737
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When exploring a vertex, make sure to explore in the order that the
     * adjacency list returns the neighbors to you. Failure to do so may cause
     * you to lose points.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List},
     * {@code java.util.Queue}, and any classes that implement the
     * aforementioned interfaces, as long as it is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
                                            Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("start or graph is null");
        }
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException(
                    "{@code start} doesn't exist in the graph");
        }

        Set<Vertex<T>> explored = new HashSet<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        List<Vertex<T>> returnVertices = new ArrayList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex<T> u = queue.remove();
            if (!explored.contains(u)) {
                returnVertices.add(u);
            }
            if (!explored.contains(u)) {
                explored.add(u);
                for (Edge<T> edge: graph.getAdjList().get(u)) {
                    queue.add(edge.getV());
                }
            }
        }
        return returnVertices;
    }


    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When deciding which neighbors to visit next from a vertex, visit the
     * vertices in the order presented in that entry of the adjacency list.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * most if not all points for this method.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List}, and
     * any classes that implement the aforementioned interfaces, as long as it
     * is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
                                            Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("start or graph is null");
        }
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException(
                    "{@code start} doesn't exist in the graph");
        }



        Set<Vertex<T>> visited = new HashSet<>();
        List<Vertex<T>> returnVertices = new ArrayList<>();
        dfsHelper(start, graph, visited, returnVertices);

        return returnVertices;
    }

    /**
     * Helper method for depthFirstSearch()
     *
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @param visited node that has already been visit
     * @param <T> the generic typing of the data
     * @param returnVertices pass out the return value
     */
    private static <T> void dfsHelper(Vertex<T> start, Graph<T>
            graph, Set<Vertex<T>> visited, List<Vertex<T>> returnVertices) {
        visited.add(start);
        returnVertices.add(start);
        for (Edge<T> edge: graph.getAdjList().get(start)) {
            if (!visited.contains(edge.getV())) {
                dfsHelper(edge.getV(), graph, visited, returnVertices);
            }
        }
    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and {@code java.util.Set} and any class that
     * implements the aforementioned interfaces, as long as it's efficient.
     *
     * You should implement the version of Dijkstra's where you terminate the
     * algorithm once either all vertices have been visited or the PQ becomes
     * empty.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null, or if start
     *  doesn't exist in the graph.
     * @param <T> the generic typing of the data
     * @param start index representing which vertex to start at (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every other node
     *         in the graph
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                      Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("start or graph is null");
        }
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException(
                    "{@code start} doesn't exist in the graph");
        }

        Map<Vertex<T>, Integer> dist = new HashMap<>();
        Set<Vertex<T>> visited = new HashSet<>();
        PriorityQueue<Edge<T>> queue = new PriorityQueue<>();

        for (Vertex<T> vert: graph.getVertices()) {
            dist.put(vert, Integer.MAX_VALUE);
        }

        Edge<T> edge1 = new Edge<>(start, start, 0);
        queue.add(edge1);
        while (!queue.isEmpty()) {
            Edge<T> shortest = queue.remove();
            if (!visited.contains(shortest.getV())) {
                visited.add(shortest.getV());
                dist.put(shortest.getV(), shortest.getWeight());
                for (Edge<T> edge: graph.getAdjList().get(shortest.getV())) {
                    if (!visited.contains(edge.getV())) {
                        Edge<T> newEdge = new Edge<>(edge.getU(), edge.getV(),
                                shortest.getWeight() + edge.getWeight());
                        queue.add(newEdge);
                    }
                }
            }
        }
        return dist;
    }


    /**
     * Runs Prim's algorithm on the given graph and return the Minimal
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is  in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * opposite edge to the set as well. This is for testing purposes.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops into the MST.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface, as long as it's efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for this method (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("start or graph is null");
        }
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException(
                    "{@code start} doesn't exist in the graph");
        }

        Map<Vertex<T>, Integer> d = new HashMap<>();
        d.put(start, 0);
        for (Vertex<T> vert: graph.getVertices()) {
            if (!vert.equals(start)) {
                d.put(vert, Integer.MAX_VALUE);
            }
        }

        Set<Edge<T>> t = new HashSet<>();
        Set<Vertex<T>> visited = new HashSet<>();
        visited.add(start);
        PriorityQueue<Edge<T>> q = new PriorityQueue<>();
        for (Edge<T> edge: graph.getAdjList().get(start)) {
            q.add(edge);
        }

        while (!q.isEmpty()) {
            Edge<T> ue = q.remove();
            if (!visited.contains(ue.getV())) {
                t.add(ue);
                Edge<T> newEdge = new Edge<>(ue.getV(), ue.getU(), ue
                        .getWeight());
                t.add(newEdge);
                visited.add(ue.getU());
                visited.add(ue.getV());
            }

            for (Edge<T> edge: graph.getAdjList().get(ue.getV())) {
                if (!visited.contains(edge.getV())) {
                    q.add(edge);
                }
            }
        }

        if (t.size() >= graph.getVertices().size() - 1) {
            return t;
        } else {
            return null;
        }
    }
}