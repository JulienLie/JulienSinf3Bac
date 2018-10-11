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

  private final TreeMap<Integer> list;

    public GlobalWarmingImpl(int[][] altitude) {
        super(altitude);
        list = new TreeMap<>();
        for(int[] tab : altitude){
          for(int i : tab){
            Integer nbr = list.get(i);
            if(nbr == null){
              list.put(i, 1);
            }
            else{
              list.replace(i, nbr+1);
            }
          }
        }
    }


    public int nbSafePoints(int waterLevel) {
        SortedMap<Integer> sub = list.headMap(waterLevel);
        int sum = 0;
        for(Integer i : sub.values()){
          sum += i;
        }
        return sum;
    }

}
