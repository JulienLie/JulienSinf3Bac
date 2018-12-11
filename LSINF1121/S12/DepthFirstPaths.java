package S12;

//TODO import

import java.util.ArrayList;

public class DepthFirstPaths {
    private boolean[] marked; // marked[v] = is there an s-v path?
    private int[] edgeTo;     // edgeTo[v] = last edge on s-v path
    private final int s;

    /**
     * Computes a path between s and every other vertex in graph G
     * @param G the graph
     * @param s the source vertex
     */
    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    // Depth first search from v
    private void dfs(Graph G, int v) {
        for(int w : G.adj(v)){
            if(!marked[w]) {
                marked[w] = true;
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    /**
     * Is there a path between the source s and vertex v?
     * @param v the vertex
     * @return true if there is a path, false otherwise
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * Returns a path between the source vertex s and vertex v, or
     * null if no such path
     * @param v the vertex
     * @return the sequence of vertices on a path between the source vertex
     *         s and vertex v, as an Iterable
     */
    public Iterable<Integer> pathTo(int v) {
        if(!marked[v]) return null;
        ArrayList<Integer> path = new ArrayList<>();
        int cur = v;
        path.add(cur);
        while(cur != this.s){
            cur = edgeTo[cur];
            path.add(cur);
        }
        return path;
    }
}
