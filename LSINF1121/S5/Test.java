package S5;

import S5.BinarySearchST;

import java.util.Random;

public class Test{

  static class Personne{
    final String nom;
    final String prenom;
    final int age;

    Personne(String nom, String prenom, int age){
      this.nom = nom;
      this.prenom = prenom;
      this.age = age;
    }

    public String toString(){
      return String.format("%s, %s (%d)", nom, prenom, age);
    }
  }

  public static void main(String[] args){
    Personne p1 = new Personne("Lienard", "Julien", 18);
    Personne p2 = new Personne("Terrieur", "Alain", 42);
    Personne p3 = new Personne("Terrieur", "Alex", 69);
    BinarySearchST<Integer, Personne> bs = new BinarySearchST<>();
    SequentialSearchST<Integer, Personne> ss = new SequentialSearchST<>();
    BinarySearchTreeST<Integer, Personne> bst = new BinarySearchTreeST<>();
//     System.out.println(bs);
//     bs.put(1, p1);
//     System.out.println(bs);
//     bs.put(3, p3);
//     System.out.println(bs);
//     bs.put(2, p2);
//     System.out.println(bs);
//     p1 = bs.get(1);
//     System.out.println(p1);
//     p2 = bs.get(2);
//     System.out.println(p2);
//     p3 = bs.get(3);
//     System.out.println(p3);
      Random r = new Random();
     for(int i = 0; i < 10000; i++){
         if(i != 568) {
             Personne p = new Personne(randomName(r.nextInt(5) + 5), randomName(r.nextInt(3) + 5), r.nextInt(120));
             ss.put(i, p);
             bs.put(i, p);
             bst.put(i, p);
             //System.out.println(p);
         }
     }
     for(int i = 0; i < 10000-1; i++){
       System.out.println(bs.get(i) + "|" + ss.get(i) + "|" + bst.get(i));
     }
     // System.out.println(BinarySearchTreeST.isBST(bst));
//    String find = "julien";
//    long time = System.currentTimeMillis();
//    String nom = randomName(find.length());
//    long i = 0;
//    long j = 0;
//    for(; !nom.equals(find); i++){
//      nom = randomName(find.length());
//      if(i % Math.pow(10,j) == 0) System.out.println(i + " " + nom + "(" + longToString(System.currentTimeMillis()-time) + ")");
//      if(i % Math.pow(10, j+1) == 0) j++;
//      //if(i==10*3||i==10*6||i==10*9||i==10*12||i==10*15) System.out.println(i + " " + nom);
//    }
//    System.out.println(i + " " + nom);
//    System.out.println(longToString(System.currentTimeMillis()-time));
  }

  private static String randomName(int size){
    Random ran = new Random();
    String lettres = "aertuiopsdfghjlmcvbny";
    String out = "";
    for(int i = 0; i < size; i++){
      out += lettres.charAt(ran.nextInt(lettres.length()));
    }
    return out;
  }

  private static String longToString(long l){
    java.util.Calendar date = new java.util.Calendar.Builder().setInstant(l).build();
    String out = "";
    if(date.get(java.util.Calendar.HOUR)-1 != 0) out += date.get(java.util.Calendar.HOUR)-1 +"h";
    if(date.get(java.util.Calendar.MINUTE) != 0) out += date.get(java.util.Calendar.MINUTE) +"m";
    if(date.get(java.util.Calendar.SECOND) != 0) out += date.get(java.util.Calendar.SECOND) +"s";
    out += date.get(java.util.Calendar.MILLISECOND) +"ms";
    return out;
  }
}
