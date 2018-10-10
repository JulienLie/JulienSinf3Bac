import java.util.Arrays;

public class Median {

	public static void main(String[] args){
		long start = System.currentTimeMillis();
		for(int j = 0; j < 10000000; j++){
		Vector a = randomVector(125);
		// System.out.print("start:");
		// for(int i = 0; i < 9; i++){
		// 	System.out.print(a.get(i) + " ");
		// }
		//System.out.println();
		int val = median(a, 0, a.size()-1);
		boolean fal = isMedian(a, 0, a.size()-1, val);
		if(!fal) System.out.println("NON!");
		}
		System.out.println(System.currentTimeMillis()-start);
	}

	public static boolean isMedian(Vector a, int lo, int hi, int median) {
		int le = 0;
		int eq = 0;
		for (int i = lo; i <= hi; i++) {
			if (a.get(i) == median) {
				eq++;
			}
			else if (a.get(i) < median) {
				le++;
			}
		}
		if (eq == 0) return false;
		return le <= a.size()/2 && le+eq > a.size()/2;
	}

	public static Vector randomVector(int n) {
			java.util.Random rand = new java.util.Random();
			int [] array = new int[n];
			Arrays.setAll(array, i -> rand.nextInt(1000000));
			Vector v = new Vector(array.length);
			for (int i = 0; i < v.size(); i++) {
					v.set(i,array[i]);
			}
			return v;
	}

    public static int median(Vector a, int lo, int hi) {
			int pivot = (hi-lo)/2 + lo;
			pivot = part(a, lo, hi, pivot);
			if(pivot == a.size()/2) return a.get(pivot);
			else if(pivot > a.size()/2) return median(a, lo, pivot-1);
			else return median(a, pivot+1, hi);
    }

		private static int part(Vector a, int lo, int hi, int pivot){
			a.swap(pivot, hi);
			int j = lo;
			for(int i = lo; i < hi; i++){
				if(a.get(i) <= a.get(hi)){
					a.swap(i, j);
					j++;
				}
			}
			a.swap(hi, j);
			return j;
		}
}
