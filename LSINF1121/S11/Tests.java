package S11;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

import static org.junit.Assert.*;

public class Tests {

    final int [] seeds = new int[]{0,7,13};

    Random rand = new Random(seeds[new java.util.Random().nextInt(3)]);

    abstract class TimeLimitedCodeBlock {

        public boolean run(long time) {
            final Runnable stuffToDo = new Thread() {
                @Override
                public void run() {
                    codeBlock();
                }
            };

            final ExecutorService executor = Executors.newSingleThreadExecutor();
            final Future future = executor.submit(stuffToDo);
            executor.shutdown(); // This does not cancel the already-scheduled task.
            boolean ok = true;
            try {
                future.get(time, TimeUnit.MILLISECONDS);
            } catch (InterruptedException ie) {
                ok = false;
            } catch (ExecutionException ee) {
                ok = false;
            } catch (TimeoutException te) {
                ok = false;
            }
            if (!executor.isTerminated()) {
                future.cancel(true);
                executor.shutdownNow();
                executor.shutdownNow(); // If you want to stop the code that hasn't finished.
            }
            return ok;
        }

        public abstract void codeBlock();
    }

    public int [][] getSimpleMatrix() {
        int[][] matrix = new int[][]{
                {0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 1, 1, 1},
                {0, 0, 0, 0, 0, 1, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 1, 1, 0},
                {0, 1, 0, 0, 0, 1, 1, 1, 1, 1},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        return matrix;
    }


    public int [][] getExamMatrix() {
        int [][] tab = new int[][] {{1,3,3,1,3},
                {4,2,2,4,5},
                {4,4,1,4,2},
                {1,4,2,3,6},
                {1,1,1,6,3}};
        return tab;
    }


    public int [][] getExamMatrix10() {
        int [][] tab = new int[][] {
                {2, 0, 5, 8, 3, 0, 3, 3, 6, 4},
                {0, 6, 5, 5, 2, 4, 4, 9, 0, 4},
                {3, 8, 2, 9, 0, 7, 4, 2, 0, 5},
                {1, 8, 3, 5, 4, 1, 8, 7, 9, 8},
                {0, 3, 1, 0, 7, 6, 8, 3, 6, 1},
                {1, 0, 3, 2, 5, 2, 9, 1, 5, 8},
                {5, 2, 7, 3, 0, 3, 5, 8, 9, 9},
                {8, 9, 1, 9, 4, 9, 6, 8, 7, 5},
                {8, 4, 0, 3, 0, 7, 9, 7, 3, 4},
                {7, 3, 4, 9, 3, 7, 0, 1, 0, 3}};
        return tab;
    }




    public int [][] getRandomMatrix(int n,int bound) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt(bound);
            }
        }
        return matrix;
    }

    public static boolean relaxedTestNbIsland(GlobalWarming gw, int expected,String s) {

        int res = gw.nbIslands();
        int res2 = (expected + (gw.altitude.length*gw.altitude.length)-gw.nbSafePointsCorrect(gw.waterLevel));
        System.out.println("TO CHANGE : "+ s + "  = "+res2);
        return res == expected || res == res2;
    }

    public static GlobalWarming.Point point(int x, int y) {
        return new GlobalWarming.Point(x,y);
    }

    @Test
    public void testOnSameIslandExam() {
        GlobalWarming gw =  new GlobalWarmingImpl(getExamMatrix(),3);
        boolean ok1 = !gw.onSameIsland(new GlobalWarming.Point(1, 3), new GlobalWarming.Point(3, 4));
        boolean ok2 = gw.onSameIsland(new GlobalWarming.Point(1, 3), new GlobalWarming.Point(1, 4));
        boolean ok3 = gw.onSameIsland(new GlobalWarming.Point(1, 3), new GlobalWarming.Point(2, 3));
        boolean ok4 = !gw.onSameIsland(new GlobalWarming.Point(2, 3), new GlobalWarming.Point(3, 4));
        System.out.println("onsameIslandsExam:"+ok1+" "+ok2+" "+ok3+" "+ok4);
        assertTrue(ok1 && ok2 && ok3 && ok4);
    }

    @Test
    public void testNbIslandsExam() {
        GlobalWarming g =  new GlobalWarmingImpl(getExamMatrix(),3);

        boolean ok = g.nbIslands()==4 || g.nbIslands()==20;

        assertTrue("islands returned (should be 4):"+g.nbIslands(),ok);
    }


    @Test
    public void testSimpleAll() {
        int [][] matrix = getSimpleMatrix();
        GlobalWarming warming = new GlobalWarmingImpl(matrix,0);

        assertTrue("error in nbIslands"+ warming.nbIslands(),warming.nbIslands()==4 ||warming.nbIslands()==80);
        //assertFalse("error in onSameIsland",warming.onSameIsland(point(0,0),point(0,0)));
        assertFalse("error in onSameIsland",warming.onSameIsland(point(0, 0), point(0, 1)));
        assertTrue("error in onSameIsland",warming.onSameIsland(point(4,5),point(1,7)));

    }

    @Test
    public void testCorrectnessOnSameIsland() {
        int level = 200000;
        GlobalWarming.Point p1 = point(10,10);
        GlobalWarming.Point p2 = point(15,15);


        for (int k = 0; k < 100; k++) {
            int [][] matrix = getRandomMatrix(100,1000000);
            GlobalWarming g1 = new GlobalWarmingImpl(matrix,level);

            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100-3; j++) {
                    if (matrix[i][j] > level && matrix[i][j+1] > level && matrix[i][j+2] > level) {
                        assertTrue(g1.onSameIsland(point(i,j),point(i,j+2)));

                    }
                }
            }
        }
        int [][] matrix = getSimpleMatrix();
        GlobalWarming warming = new GlobalWarmingImpl(matrix,0);
        assertFalse(warming.onSameIsland(point(0,0),point(0,0)));
        assertFalse(warming.onSameIsland(point(0, 0), point(0, 1)));
        assertTrue(warming.onSameIsland(point(4,5),point(1,7)));

    }

    @Test
    public void testCorrectnessNbIslands() {

        int level = 200000;


        int[][] matrix = getExamMatrix10();

        GlobalWarming warming = new GlobalWarmingImpl(matrix, 20);
        assertTrue(warming.nbIslands()==20 || warming.nbIslands()==100);

        warming = new GlobalWarmingImpl(matrix, -20);
        assertTrue(warming.nbIslands()==1);

        matrix[0][0] = 30;
        matrix[0][1] = 30;
        matrix[0][9] = 30;
        matrix[9][0] = 30;
        matrix[9][9] = 30;
        matrix[9][8] = 30;

        warming = new GlobalWarmingImpl(matrix, 25);
        assertTrue(warming.nbIslands()==4 ||warming.nbIslands()==98);



        for (int iter = 0; iter < 100; iter++) {


            matrix = new int[100][100];

            boolean [] generated = new boolean[10];
            int nIslandExpected = 0;
            int k = 0;
            int above = 0;
            int count = 0;
            for (int i = 0; i < rand.nextInt(10); i++) {
                count = 0;
                k = rand.nextInt(8);
                matrix[k*10][k*10] = 1;
                matrix[k*10+1][k*10] = 1;
                matrix[k*10][k*10+1] = 1;
                matrix[k*10+1][k*10+1] = 1;
                if (rand.nextBoolean() && !generated[k]) {
                    matrix[k*10+2][k*10+1] = 1;
                    count++;
                }
                if (rand.nextBoolean() && !generated[k]) {
                    matrix[k*10+2][k*10] = 1;
                    count++;
                }
                if (!generated[k]) {
                    generated[k] = true;
                    nIslandExpected += 1;
                    above+= 4+count;
                }
            }

            warming = new GlobalWarmingImpl(matrix, 0);
            assertTrue(warming.nbIslands()==nIslandExpected || warming.nbIslands()==nIslandExpected+10000-above);

        }

        matrix = getSimpleMatrix();
        warming = new GlobalWarmingImpl(matrix,0);
        assertTrue(warming.nbIslands()==4 ||warming.nbIslands()==80);
    }


    @Test
    public void timeComplexityConstructorCorrect() {
        try {

            final int [][] matrix = getRandomMatrix(400,2000000);

            boolean timeOk = new TimeLimitedCodeBlock() {
                @Override
                public void codeBlock() {
                    int max = 0;

                    // do some computation here
                    long t0 = System.currentTimeMillis();
                    GlobalWarming g = new GlobalWarmingImpl(matrix,1000000 );
                    long t1 = System.currentTimeMillis();
                    System.out.println("time constructor:"+(t1-t0));


                }
            }.run(500);
            assertTrue(timeOk);

        } catch (AssertionError e) {
            assertTrue(false);
        }
    }



    @Test
    public void timeComplexityNbIslands() {
        try {

            final int [][] matrix = getRandomMatrix(500,2000000);
            final GlobalWarming g = new GlobalWarmingImpl(matrix,1000000 );

            boolean timeOk = new TimeLimitedCodeBlock() {
                @Override
                public void codeBlock() {
                    int max = 0;

                    // do some computation here
                    long t0 = System.currentTimeMillis();

                    g.nbIslands();

                    long t1 = System.currentTimeMillis();
                    System.out.println("time nbIslands:"+(t1-t0));

                }
            }.run(5);
            assertTrue(timeOk);

        } catch (AssertionError e) {
            assertTrue(false);
        }
    }



    @Test
    public void timeComplexityOnSameIsland() {
        try {

            final int [][] matrix = getRandomMatrix(500,2000000);
            final GlobalWarming g = new GlobalWarmingImpl(matrix,1000000 );

            boolean timeOk = new TimeLimitedCodeBlock() {
                @Override
                public void codeBlock() {
                    int max = 0;
                    // do some computation here
                    long t0 = System.currentTimeMillis();
                    int n = matrix.length;
                    for (int i = 0; i < n; i++){
                        for (int j = 0; j < n; j++) {
                            g.onSameIsland(point(i,j),point(n-1,n-1));
                        }
                    }
                    long t1 = System.currentTimeMillis();
                    System.out.println("time onSameIsland:"+(t1-t0));
                }
            }.run(500);
            assertTrue(timeOk);

        } catch (AssertionError e) {
            assertTrue(false);
        }
    }

}
