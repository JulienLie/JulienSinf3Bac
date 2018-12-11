package S12;

import java.util.LinkedList;

public class ConnectedComponents {

    private static class UF{
        int[] ids;
        int nbr;

        UF(int nbr){
            this.ids = new int[nbr];
            for(int i = 0; i < nbr; i++)
                this.ids[i] = i;
            this.nbr = nbr;
        }

        int find(int i){
            int p = i;
            while (ids[p] != p){
                p = ids[p];
            }
            return p;
        }

        void union(int i, int j){
            int p = find(i);
            int q = find(j);
            if(ids[p] != ids[q]) {
                ids[p] = ids[q];
                nbr--;
            }
        }
    }

    // 1 2 3 4 5 6 7

    /**
     * @return the number of connected components in g
     */
    public static int numberOfConnectedComponents(Graph g) {
        if(g.V() <= 1) return g.V();
        UF uf = new UF(g.V());
        for(int i = 1; i < g.V(); i++){
            for(int j : g.adj(i)){
                uf.union(i, j);
            }
        }
        return uf.nbr;
    }
}