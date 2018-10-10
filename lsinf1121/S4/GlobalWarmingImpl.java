import java.util.*;

public class GlobalWarmingImpl extends GlobalWarming {

  public static void main(String[] args){
    int [][] tab = new int[][] {{1,3,3,1,3},
                          {4,2,2,4,5},
                          {4,4,1,4,2},
                          {1,4,2,3,6},
                          {1,1,1,6,3}};
    GlobalWarming gw = new GlobalWarmingImpl(tab);
    System.out.println(gw.nbSafePoints(2));
  }

  private final LinkedList<Integer> list;

    public GlobalWarmingImpl(int[][] altitude) {
        super(altitude);
        list = new LinkedList<>();
        for(int[] tab : altitude){
          for(int i : tab){
            list.add(i);
          }
        }
    }


    public int nbSafePoints(int waterLevel) {
        
        return sub.size();
    }

}
