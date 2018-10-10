import junit.framework.TestCase;

import java.util.Arrays;

public class Test extends TestCase {
	
	// assigning the values
    protected void setUp() {

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

    @org.junit.Test
    public void testMedianOk() {
        for (int i = 100; i < 1000; i += 100) {
            Vector v = randomVector(i+1);
            int m = Median.median(v,0,v.size()-1);
            assertTrue("correct median value computed",isMedian(v,0,v.size()-1,m));
        }
    }

}

