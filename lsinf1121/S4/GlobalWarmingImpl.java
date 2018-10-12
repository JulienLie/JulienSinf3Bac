import java.util.*;

public class GlobalWarmingImpl extends GlobalWarming {

  public static void main(String[] args){
    Random rand = new Random();
    int n = rand.nextInt(10);
    int [][] tab = new int[n][n];
    for(int i = 0; i < n; i++){
      for(int j = 0; j < n; j++){
        tab[i][j] = rand.nextInt(7);
        System.out.print(tab[i][j] + "\t");
      }
      System.out.println();
    }
    GlobalWarming gw = new GlobalWarmingImpl(tab);
    n = rand.nextInt(7);
    System.out.println("point for " + n + ":" + gw.nbSafePoints(n));
  }

  private final int[] list;

    public GlobalWarmingImpl(int[][] altitude) {
        super(altitude);
        list = new int[altitude.length*altitude.length];
        for(int i = 0; i < altitude.length; i++){
          for(int j = 0; j < altitude.length; j++){
            list[i+(altitude.length*j)] = altitude[i][j];
          }
        }
        Arrays.sort(list);
    }


    public int nbSafePoints(int waterLevel) {
      if(list.length == 0){
        return 0;
      }
      if(waterLevel < 0){
            return this.list.length;
        }
        else if(waterLevel >= list[list.length-1]){
          return 0;
        }
      int bin = -1;
      for(int i = 1; bin < 0; i++){
        bin = Arrays.binarySearch(this.list, waterLevel+i);
      }
      while(bin >= 0 && list[bin] > waterLevel){
        bin--;
      }
      return this.list.length-bin-1;
    }
}
