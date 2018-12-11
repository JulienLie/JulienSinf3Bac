package S12;

import java.util.*;

public class GlobalWarmingImpl extends GlobalWarming {

    private final int[][] carre = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    /**
     * In the following, we assume that the points are connected to
     * horizontal or vertical neighbors but not to the diagonal ones
     * @param altitude is a n x n matrix of int values representing altitudes (positive or negative)
     * @param waterLevel is the water level, every entry <= waterLevel is flooded
     */
    public GlobalWarmingImpl(int[][] altitude, int waterLevel) {
        super(altitude,waterLevel);
    }


    /**
     *
     * @param p1 a safe point with valid coordinates on altitude matrix
     * @param p2 a safe point (different from p1) with valid coordinates on altitude matrix
     * @return the shortest simple path (vertical/horizontal moves) if any between from p1 to p2 using only vertical/horizontal moves on safe points.
     *         an empty list if not path exists (i.e. p1 and p2 are not on the same island).
     */
    public List<Point> shortestPath(Point p1, Point p2) {
        if(this.altitude[p1.x][p1.y] <= this.waterLevel || this.altitude[p2.x][p2.y] <= this.waterLevel) return new ArrayList<>();
        if(p1.equals(p2)) return Arrays.asList(p1);
        List<Point> ret = null;
        Point[][] path = new Point[this.altitude.length][this.altitude[0].length];
        Queue<Point> toGo = new LinkedList<>();
        toGo.offer(p1);
        path[p1.x][p1.y] = p1;
        while(!toGo.isEmpty()){
            Point p = toGo.poll();
            for(int[] i : carre){
                int x = p.x + i[0];
                int y = p.y + i[1];
                if(x >= 0 && x < this.altitude.length && y >= 0 && y < this.altitude[x].length && this.altitude[x][y] > this.waterLevel && path[x][y] == null){
                    path[x][y] = p;
                    if(p2.equals(new Point(x, y))){
                        ret = pathBack(path, p1, p2);
                        toGo = new LinkedList<>();
                    }
                    else{
                        toGo.offer(new Point(x, y));
                    }
                }
            }
        }
        if(ret == null) ret = new LinkedList<>();
        return ret;
    }

    public List<Point> pathBack(Point[][] path, Point s, Point a){
        LinkedList<Point> ret = new LinkedList<>();
        Point cur = a;
        while(cur != s){
            ret.addFirst(cur);
            cur = path[cur.x][cur.y];
        }
        ret.addFirst(cur);
        return ret;
    }

}