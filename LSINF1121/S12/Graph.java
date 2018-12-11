package S12;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph {

    private final int V;
    private int E;
    private LinkedList<Integer>[] adj;

    public Graph(int V){
        this.V = V;
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for(int i = 0; i < V; i++)
            adj[i] = new LinkedList<>();
    }

    public Graph(InputStream in){
        this(new Scanner(in));
    }

    public Graph(Scanner in){
        this(in.nextInt());
        int E = in.nextInt();
        for(int i = 0; i < this.E; i++){
            int v = in.nextInt();
            int w = in.nextInt();
            addEdge(v, w);
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public String toString(){
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < V; v++){
            s += v + ": ";
            for (int w : this.adj(v))
                s += w + " ";
            s += "\n";
        }
        return s;
    }
}
