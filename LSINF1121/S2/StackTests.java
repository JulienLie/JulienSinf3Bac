import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.EmptyStackException;

public class StackTests {

  @Test
  public void firstTest() {
      Stack<Integer> stack = new MyStack<Integer>();
      stack.push(1);
      assertEquals(new Integer(1), stack.pop());
  }

  @Test
  public void secondTest() {
      Stack<Double> stack = new MyStack<>();
      assertEquals(true, stack.empty());
      stack.push(0.2688);
      assertEquals(false, stack.empty());
      assertEquals((Double)0.2688,stack.peek());
      assertEquals((Double)0.2688,stack.pop());
      for(double i = 0; i < 126; i++){
        stack.push(i*65.0/789 + 69 -42 * 6);
      }
      for(double i = 125; i >= 0; i--){
        Double eq = i*65.0/789+69-42*6;
        assertEquals(eq,stack.pop());
      }
      assertEquals(true, stack.empty());
  }

  @Test(expected = EmptyStackException.class)
  public void execptionSeek() {
    Stack<Long> stack = new MyStack<>();
    stack.peek();
  }

  @Test(expected = EmptyStackException.class)
  public void execptionPop() {
    Stack<Long> stack = new MyStack<>();
    stack.pop();
  }

}
