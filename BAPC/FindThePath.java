import java.util.Scanner;
import java.util.LinkedList;
import java.util.Collections;

public class FindThePath{

  private static boolean find = false;

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    String line = in.nextLine();
    String[] splited = line.split(" ");
    int nNode = 0, nLink = 0;
    try{
      nNode = Integer.parseInt(splited[0]);
      nLink = Integer.parseInt(splited[1]);
    }catch(Exception e){
      e.printStackTrace();
      System.exit(-1);
    }

    LinkedList<LinkedList<Integer>> graph = new LinkedList<LinkedList<Integer>>();
    for(int i = 0; i < nNode; i++){
      graph.add(new LinkedList<Integer>());
    }
    for(int i = 0; i < nLink; i++){
      line = in.nextLine();
      splited = line.split(" ");
      int f = 0, s = 0;
      try{
        f = Integer.parseInt(splited[0]);
        s = Integer.parseInt(splited[1]);
      }catch(Exception e){
        e.printStackTrace();
        System.exit(-1);
      }
      graph.get(f).add(s);
    }

    line = in.nextLine();
    splited = line.split(" ");
    int src = 0, dest = 0;
    try{
      src = Integer.parseInt(splited[0]);
      dest = Integer.parseInt(splited[1]);
    }catch(Exception e){
      e.printStackTrace();
      System.exit(-1);
    }

    LinkedList<Integer> res = rec(graph, src, dest, new LinkedList<Integer>());
    Collections.reverse(res);

    if(res.isEmpty()){
      System.out.println("impossible");
    }
    else{
      //System.out.println("res: ");

      for(int i : res){
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }

  private static LinkedList<Integer> rec(LinkedList<LinkedList<Integer>> graph, int s, int t, LinkedList<Integer> visited){
    LinkedList<Integer> toRet = new LinkedList<>();
    visited.add(s);
    if(find) return toRet;
    if(s == t){
      toRet.add(t);
      return toRet;
    }
    for(int i : graph.get(s)){
      if(visited.indexOf(i) == -1){
        LinkedList<Integer> rec = rec(graph, i, t, visited);
        if(!rec.isEmpty()){
          toRet.addAll(rec);
          toRet.add(s);
          return toRet;
        }
      }
    }
    return toRet;
  }
}
