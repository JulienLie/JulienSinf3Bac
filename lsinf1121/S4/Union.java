import java.util.LinkedList;
import java.util.Arrays;
import java.util.Collections;

public class Union{

  public static void main(String[] args){
    Interval i0 = new Interval(10, 10);
    Interval i1 = new Interval(2, 4);
    Interval i2 = new Interval(3, 4);
    Interval i3 = new Interval(5, 6);
    Interval i4 = new Interval(6, 9);
    Interval i5 = new Interval(6, 8);
    Interval i6 = new Interval(1, 3);
    Interval[] inter = union(new Interval[]{i6, i6});
    for(Interval i : inter){
      System.out.print(i);
    }
    System.out.println();
  }

    public static Interval[] union(Interval[] intervals){
        if(intervals.length == 0){
            return new Interval[]{};
        }
        Arrays.sort(intervals);
        for(Interval i : intervals){
          System.out.print(i);
        }
        System.out.println();
        LinkedList<Interval> newInter = new LinkedList<>(Arrays.asList(intervals));
        for(int i = 0; i < newInter.size()-1; i++){
          Interval cur = newInter.get(i);
          Interval next = newInter.get(i+1);
          System.out.printf("Check %s avec %s ", cur, next);
          if(next.min <= cur.max){
            if(cur.max >= next.max){
              System.out.println("donc on remove " + next);
              newInter.remove(i+1);
              i--;
            }
            else{
              newInter.remove(i);
              newInter.remove(i);
              Interval toAdd = new Interval(cur.min, next.max);
              System.out.println("et ca donne " + toAdd);
              newInter.add(i, toAdd);
              i--;
            }
          }
          else System.out.println("et y'a rien a faire");
        }
        return newInter.toArray(new Interval[]{});
    }
}
