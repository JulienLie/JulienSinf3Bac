package S4;

import java.util.*;

public class Union{

    public static void main(String[] args){
        Interval i0 = new Interval(10, 10);
        Interval i1 = new Interval(2, 4);
        Interval i2 = new Interval(3, 4);
        Interval i3 = new Interval(5, 6);
        Interval i4 = new Interval(6, 9);
        Interval i5 = new Interval(6, 8);
        Interval i6 = new Interval(1, 3);
        Interval[] in = new Interval[]{i0, i1, i2, i3, i4, i5, i6};
        Interval[] inter = union(in);
        //Interval[] inter2 = unionMerge(in);
        // if(inter.length != inter2.length){
        //   System.out.println("Pas la meme taille " + inter.length + " " + inter2.length);
        // }
        for(Interval i : inter){
            System.out.print(i);
        }
        System.out.println();
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
}
