package S11;

import java.util.*;

public class GlobalWarmingImpl extends GlobalWarming {

    private class UF{
        Point[][] id;
        int[][] sz;
        int count;

        UF(int l, int c){
            count = l*c;
            id = new Point[l][c];
            sz = new int[l][c];
            for(int i = 0; i < l; i++){
                for(int j = 0; j < c; j++){
                    id[i][j] = new Point(i, j);
                    sz[i][j] = 1;
                }
            }
        }

        boolean connected(Point p, Point q){
            Point i = find(p);
            Point j = find(q);
            if(i.x < 0 || i.y < 0 || j.x < 0 || j.y < 0) return false;
            return i.equals(j);
        }

        Point find(Point p){
            while(!p.equals(id[p.x][p.y])) p = id[p.x][p.y];
            return p;
        }

        void union(Point p, Point q){
            Point i = find(p);
            Point j = find(q);
            if(i.equals(j)) return;
            if(sz[i.x][i.y] > sz[j.x][j.y]){
                id[i.x][j.y] = j;
                sz[j.x][j.y] += sz[i.x][i.y];
            }
            else{
                id[j.x][j.y] = i;
                sz[i.x][i.y] = sz[j.x][j.y];
            }
            count--;
        }
    }

    private UF uf;


    public GlobalWarmingImpl(int[][] altitude, int waterLevel) {
        super(altitude,waterLevel);
        // expected pre-processing time in the constructror : O(n^2 log(n^2))
        uf = new UF(altitude.length, altitude[0].length);
        final int[][] carre = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        for(int i = 0; i < altitude.length; i++)
            for (int j = 0; j < altitude[i].length; j++)
                if(altitude[i][j] > waterLevel)
                    for (int[] pos : carre)
                        if(i+pos[0] >= 0 && j+pos[1] >= 0 && i+pos[0] < altitude.length && j+pos[1] < altitude[0].length && altitude[i+pos[0]][j+pos[1]] > waterLevel)
                            uf.union(new Point(i, j), new Point(i+pos[0], j+pos[1]));
    }

    public int nbIslands() {
        // expected time complexity O(1)
        return uf.count;
    }


    public boolean onSameIsland(Point p1, Point p2) {
        // expected time complexity O(1)
        if(altitude[p1.x][p1.y] <= waterLevel || altitude[p2.x][p2.y] <= waterLevel) return false;
        return uf.connected(p1, p2);
    }

}