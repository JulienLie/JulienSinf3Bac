package S7.LSINF1121_PART3_UnitTestsRedBlackTree.src.test.java;

import S7.LSINF1121_PART3_UnitTestsRedBlackTree.src.main.java.MyRedBlack;
import S7.LSINF1121_PART3_UnitTestsRedBlackTree.src.main.java.RedBlack;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class RedBlackTests {

    @Test
    public void testEmpty() {
        RedBlack<Integer, String> rb = new MyRedBlack<>();
        assertTrue(rb.isEmpty());
        rb.put(0, "julien");
        assertFalse(rb.isEmpty());
    }

    @Test
    public void testSize() {
        RedBlack<Integer, String> rb = new MyRedBlack<>();
        assertEquals(0, rb.size());
        for(int i = 0; i < 3697; i++){
            rb.put(i, "toujours la meme chose");
        }
        assertEquals(3697, rb.size());
    }

    @Test
    public void testGet(){
        RedBlack<Integer, String> rb = new MyRedBlack<>();
        assertNull(rb.get(5));
        rb.put(5, "julien");
        assertEquals("julien", rb.get(5));
        for(int i = 0; i < 6579; i++){
            if(i != 5) rb.put(i, "pas julien");
        }
        for(int i = 0; i < 6579; i++){
            if(i == 5) assertEquals(rb.get(i), "julien");
            else assertEquals(rb.get(i), "pas julien");
        }

        try{
            rb.get(null);
            fail();
        }catch(IllegalArgumentException e){}
    }

    @Test
    public void testContains(){
        RedBlack<Integer, String> rb = new MyRedBlack<>();
        for(int i = 0; i < 99826; i++){
            rb.put(i, "et encore la meme");
        }
        assertFalse(rb.contains(-1));
        assertTrue(rb.contains(42));

        try{
            rb.contains(null);
            fail();
        }catch (IllegalArgumentException e){}
    }

    @Test
    public void testPut(){
        RedBlack<Integer, String> rb = new MyRedBlack<>();
        for(int i = 0; i < 6969; i++){
            rb.put(i, "surtout pas ca");
        }
        assertEquals("surtout pas ca", rb.get(42));
        rb.put(42, "par contre ca oui");
        rb.put(69, null);
        for(int i = 0; i < 6969; i++){
            if(i == 42) assertEquals("par contre ca oui", rb.get(42));
            else if(i == 69) assertNull(rb.get(69));
            else assertEquals("surtout pas ca", rb.get(i));
        }

        try{
            rb.put(null, "oups");
            fail();
        }catch(IllegalArgumentException e){}
    }

    @Test
    public void testMinMax(){
        RedBlack<Integer, String> rb = new MyRedBlack<>();

        try{
            rb.min();
            fail();
        }catch(NoSuchElementException e){}
        try{
            rb.max();
            fail();
        }catch(NoSuchElementException e){}

        for(int i = 0; i < 9866; i++){
            rb.put(i, "woaw");
        }
        rb.put(-2, "petit");
        rb.put(26548, "GRAND");
        assertEquals(-2,(int) rb.min());
        assertEquals(26548, (int)rb.max());
        rb.put(-1, "PETIT");
        rb.put(26547, "grand");
        rb.deleteMax();
        rb.deleteMin();
        assertEquals(-1,(int) rb.min());
        assertEquals(26547,(int) rb.max());
    }

    @Test
    public void testDelete(){
        RedBlack<Integer, String> rb = new MyRedBlack<>();
        for(int i = 0; i < 289; i++){
            rb.put(i, "NOOOOOON pas moi pls");
        }
        rb.delete(123);
        assertNull(rb.get(123));

        try{
            rb.delete(null);
            fail();
        }catch(IllegalArgumentException e){}
    }

    @Test
    public void testHeight(){
        RedBlack<Integer, String> rb = new MyRedBlack<>();
        rb.put(-1, "Le prems!");
        assertEquals(0, rb.height());
        for(int i = 0; i < 569; i++){
            rb.put(i, "plus!");
        }
        assertEquals(9, rb.height());
    }

    @Test
    public void testFloorCeiling(){
        RedBlack<Integer, String> rb = new MyRedBlack<>();

        try{
            rb.ceiling(256);
            fail();
        }
        catch(NoSuchElementException e){}
        try{
            rb.floor(789);
            fail();
        }
        catch(NoSuchElementException e){}

        for(int i = 0; i < 6547; i++){
            if(i != 4269)
                rb.put(i, "yes je suis pas le 4269ieme :D");
            if(i == 4268) rb.put(i, "moi je suis juste avant");
            if(i == 4270) rb.put(i, "moi juste apres");
        }
        assertEquals(4268,(int) rb.floor(4269));
        assertEquals(4270,(int) rb.ceiling(4269));
        assertEquals(1258,(int) rb.floor(1258));
        assertEquals(791,(int) rb.ceiling(791));

        try{
            rb.ceiling(null);
            fail();
        }
        catch(IllegalArgumentException e) {}
    }

    @Test
    public void testSelect(){
        RedBlack<Character, String> rb = new MyRedBlack<>();
        for(char c = 'a'; c <= 'z'; c++){
            if(c == 'j') rb.put(c, "je suis un petit j :)");
            else rb.put(c, "Une boucle char :o");
        }
        assertEquals('j',(int) rb.select(9));

        try{
            rb.select(69);
            fail();
        }
        catch(IllegalArgumentException e){}
    }

    @Test
    public void testRank(){
        RedBlack<Integer, String> rb = new MyRedBlack<>();
        assertEquals(0, rb.rank(-689));
        for(int i = 0; i < 5897; i++){
            rb.put(i, "zzz");
        }
        assertEquals(2358, rb.rank(2358));

        try{
            rb.rank(null);
            fail();
        }
        catch(IllegalArgumentException e){}
    }

    @Test
    public void testIterable(){
        RedBlack<Integer, String> rb = new MyRedBlack<>();
        for(int i = 0; i < 458; i++){
            rb.put(i, "rholala");
        }
        assertEquals(458, rb.size());
        Iterable<Integer> ib = rb.keys();
        int in = 0;
        for(Integer i : ib){
            assertEquals(in++,(int) i);
        }
        assertEquals(rb.size(), in);
        Iterator<Integer> it = rb.keys().iterator();
        for(int i = 0; i < 458; i++){
            assertTrue(it.hasNext());
            assertEquals(i,(int) it.next());
        }
        assertFalse(it.hasNext());

        try{
            it.next();
            fail();
        }catch(NoSuchElementException e){}

        it = rb.keys().iterator();
        it.next();
        it.next();

        rb.put(896, "NOOON TU PEUX PAS!");
        try{
            it.next();
            fail();
        }catch(ConcurrentModificationException e){}
        try{
            it.hasNext();
            fail();
        }catch(ConcurrentModificationException e){}
    }

    @Test
    public void testSizeBetween(){
        RedBlack<Integer, String> rb = new MyRedBlack<>();
        for(int i = 0; i < 5698; i++){
            rb.put(i, "jsfhuçogyq!uozrhgopqzhropgtom");
        }

        assertEquals(9, rb.size(1234, 1234+8));
    }
}
