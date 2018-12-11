package S12;

//TODO Put your import here

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstShortestPaths {
    public static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked; // marked[v] = is there an s-v path
    private int[] distTo;     // distTo[v] = number of edges shortest s-v path

    /**
     * Computes the shortest path between any
     * one of the sources and very other vertex
     * @param G the graph
     * @param sources the source vertices
     */
    public BreadthFirstShortestPaths(Graph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        for (int v = 0;v < G.V();v++) {
            distTo[v] = INFINITY;
        }
        bfs(G, sources);
    }

    // Breadth-first search from multiple sources
    private void bfs(Graph G, Iterable<Integer> sources) {
        for(Integer i : sources){
            Queue<Integer> queue = new LinkedList<>();
            marked[i] = true;
            distTo[i] = 0;
            queue.offer(i);
            while(!queue.isEmpty()){
                int v = queue.poll();
                for(int w : G.adj(v)){
                    int newDist = distTo[v]+1;
                    if(newDist < distTo[w]){
                        distTo[w] = newDist;
                        marked[w] = true;
                        queue.offer(w);
                    }
                }
            }
        }
    }

    /**
     * Is there a path between (at least one of) the sources and vertex v?
     * @param v the vertex
     * @return true if there is a path, and false otherwise
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * Returns the number of edges in a shortest path
     * between one of the sources and vertex v?
     * @param v the vertex
     * @return the number of edges in a shortest path
     */
    public int distTo(int v) {
        return distTo[v];
    }
}
