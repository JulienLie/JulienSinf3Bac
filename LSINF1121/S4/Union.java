package S4;

import java.util.Arrays;
import java.util.LinkedList;

public class Union{

  public static void main(String[] args){
    Interval i0 = new Interval(4, 7);
    Interval i1 = new Interval(5, 8);
    Interval i2 = new Interval(6, 7);
    Interval i3 = new Interval(8, 11);
    Interval i4 = new Interval(12, 12);
    Interval i5 = new Interval(12, 15);
    Interval i6 = new Interval(13, 14);
    Interval i7 = new Interval(13, 15);
    Interval i8 = new Interval(14, 17);
    Interval i9 = new Interval(16, 16);
    Interval[] in = new Interval[]{i0, i1, i2, i3, i4, i5, i6, i7, i8, i9};
    Interval[] inter = union(in);
    for(Interval i : inter){
      System.out.print(i);
    }
    System.out.println();
  }

    public static Interval[] union(Interval[] intervals){
      if(intervals.length == 0) return new Interval[0];
      Arrays.sort(intervals);
      for(Interval i : intervals) System.out.print(i);
      System.out.println();
      int min = intervals[0].min;
      int max = intervals[0].max;
      LinkedList<Interval> list = new LinkedList<>();
      for(int i = 1; i < intervals.length; i++){
        System.out.printf("[%d,%d]%s ", min, max, intervals[i]);
        if(intervals[i].min > max){
          System.out.println("Nouvel interval donc ajout de [" + min + "," + max + "]");
          list.add(new Interval(min, max));
          min = intervals[i].min;
          max = intervals[i].max;
        }
        else{
          max = Math.max(intervals[i].max, max);
          System.out.println("Nouveau max donc nouvel interval [" + min +"," + max + "]");
        }
      }
      list.add(new Interval(min, max));
      return list.toArray(new Interval[0]);
    }
}
