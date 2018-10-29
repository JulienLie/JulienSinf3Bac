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
<<<<<<< HEAD
    //Interval[] inter2 = unionMerge(in);
    // if(inter.length != inter2.length){
    //   System.out.println("Pas la meme taille " + inter.length + " " + inter2.length);
    // }
=======
>>>>>>> c72208e5a7b1f8a76e2568c4c61ce0b992fb079a
    for(Interval i : inter){
      System.out.print(i);
    }
    System.out.println();
<<<<<<< HEAD
    // for(Interval i : inter2){
    //   System.out.print(i);
    // }
    System.out.println();
  }

    public static Interval[] union(Interval[] intervals){
        if(intervals.length == 0) return new Interval[0];
        Arrays.sort(intervals);
        for(Interval i : intervals){
          System.out.print(i);
        }
        System.out.println();
        int min = intervals[0].min;
        int max = intervals[0].max;
        LinkedList<Interval> list = new LinkedList<>();
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i].min > max){
                list.add(new Interval(min, max));
                min = intervals[i].min;
                max = intervals[i].max;
            }
            else{
                max = intervals[i].max;
            }
        }
        list.add(new Interval(min, max));
        return list.toArray(new Interval[0]);
    }

    public static Interval[] unionMerge(Interval[] intervals){
      LinkedList<Interval> list = new LinkedList<>(Arrays.asList(intervals));
      merge(list);
      return list.toArray(new Interval[]{});
    }

    private static void merge(List<Interval> list){
      if(list.size() <= 1){
        return;
      }
      merge(list.subList(0, list.size()/2));
      merge(list.subList(list.size()/2 + 1, list.size()));
      hopla(list);
    }

    private static void hopla(List<Interval> list){
      System.out.print("hopla ");
      for(Interval i : list){
        System.out.print(i);
      }
      System.out.println();
      int mid = list.size()/2;
      int pos = 0;
      Interval[] buf = new Interval[list.size()];
      for(int i = 0; i < list.size(); i++){
        buf[i] = list.remove(0);
      }
      int l = 0;
      int r = mid+1;
      while(l <= mid || r < list.size()){
        if(l > mid){
          list.add(pos++, buf[r++]);
          break;
        }
        else if(r >= list.size()){
          list.add(pos++, buf[l++]);
          break;
        }
        Interval f = buf[r];
        Interval s = buf[l];
        // f est avant
        if(f.min <= s.min){
          // s est contenu dans f
          if(f.max >= s.min){
            // totalement
            if(f.max >= s.max){
              r++;
            }
            // s continue f
            else{
              Interval i = new Interval(f.min, s.max);
              buf[l] = i;
              r++;
            }
          }
          // s est apres f
          else{
            list.add(pos++, buf[l++]);
          }
=======
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
>>>>>>> c72208e5a7b1f8a76e2568c4c61ce0b992fb079a
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
