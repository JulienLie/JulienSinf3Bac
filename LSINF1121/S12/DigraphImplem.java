package S12;

import java.util.LinkedList;

public class DigraphImplem implements Digraph {

    private LinkedList<Integer>[] links;
    private int E;


    public DigraphImplem(int V) {
         links = (LinkedList<Integer>[]) new LinkedList[V];
         this.E = 0;
         for(int i = 0; i < V; i++){
             links[i] = new LinkedList<>();
         }
    }

    /**
     * The number of vertices
     */
    public int V() {
        return links.length;
    }

    /**
     * The number of edges
     */
    public int E() {
        return E;
    }

    /**
     * Add the edge v->w
     */
    public void addEdge(int v, int w) {
        links[v].add(w);
        this.E++;
    }

    /**
     * The nodes adjacent to node v
     * that is the nodes w such that there is an edge v->w
     */
    public Iterable<Integer> adj(int v) {
        return links[v];
    }

    /**
     * A copy of the digraph with all edges reversed
     */
    public Digraph reverse() {
        Digraph ret = new DigraphImplem(this.links.length);
        for(int i = 0; i < this.links.length; i++){
            for(int j : links[i]){
                ret.addEdge(j, i);
            }
        }
        return ret;
    }
}
