public class MergeSort {

  public static void main(String[] args){
    java.util.Random rand = new java.util.Random();
    Integer[] tab = new Integer[rand.nextInt(50)];
    for(int i = 0; i < tab.length; i++){
      tab[i] = rand.nextInt(100000);
    }
    sort(tab);
    for(Integer i : tab){
      System.out.printf("%d ", i);
    }
    System.out.println();
  }

  /**
  * Pre-conditions: a[lo..mid] and a[mid+1..hi] are sorted
  * Post-conditions: a[lo..hi] is sorted
  */
  private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
    for (int k = lo; k <= hi; k++) {
      aux[k] = a[k];
    }

    int i = lo;
    int j = mid + 1;
    for (int k = lo; k <= hi; k++) {
      if (i > mid) {
        a[k] = aux[j++];
      } else if (j > hi) {
        a[k] = aux[i++];
      } else if (aux[j].compareTo(aux[i]) < 0) {
        a[k] = aux[j++];
      } else {
        a[k] = aux[i++];
      }
    }
  }

  // Mergesort a[lo..hi] using auxiliary array aux[lo..hi]
  private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
    if(hi-lo == 0){
      return;
    }
    int mid = (hi+lo)/2;
    sort(a, aux, lo, mid);
    sort(a, aux, mid+1, hi);
    merge(a, aux, lo, mid, hi);
  }

  /**
  * Rearranges the array in ascending order, using the natural order
  */
  public static void sort(Comparable[] a) {
    sort(a, new Comparable[a.length], 0, a.length-1);
  }
}
