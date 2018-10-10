import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<Item> implements Iterable<Item>{
  private long nOp = 0; // count the number of operations
  private int n;          // size of the stack
  private Node  last;   // trailer of the list

  // helper linked list class
  private class Node {
    private Item item;
    private Node next;
  }

  public CircularLinkedList() {
    last = null;
    n = 0;
  }

  public boolean isEmpty() { return n == 0; }

  public int size() { return n; }

  private long nOp() { return nOp; }

  /**
  * Append an item at the end of the list
  * @param item the item to append
  */
  public void enqueue(Item item) {
    Node n = new Node();
    n.item = item;
    this.nOp++;
    if(last == null){
      last = n;
      n.next = n;
    }
    else{
      n.next = last.next;
      last.next = n;
      last = n;
    }
    this.n++;
  }

  /**
  * Removes the element at the specified position in this list.
  * Shifts any subsequent elements to the left (subtracts one from their indices).
  * Returns the element that was removed from the list.
  */
  public Item remove(int index) {
    if(index < 0 || index > n-1)
      throw new IndexOutOfBoundsException();
    Item ret;
    this.nOp++;
    if(this.n == 1){
      ret = this.last.item;
      this.last = null;
      this.n--;
      return ret;
    }
    Node prec = null;
    Node toSup = this.last;
    for(int i = 0; i < index+1; i++){
      prec = toSup;
      toSup = toSup.next;
    }
    prec.next = toSup.next;
    ret = toSup.item;
    if(toSup == this.last){
      this.last = prec;
    }
    return ret;
  }

  /**
  * Returns an iterator that iterates through the items in FIFO order.
  * @return an iterator that iterates through the items in FIFO order.
  */
  public Iterator<Item> iterator() {
    return new ListIterator();
  }

  /**
  * Implementation of an iterator that iterates through the items in FIFO order.
  *
  */
  private class ListIterator implements Iterator<Item> {
    Node curr;
    long nOp;
    int pos;

    public ListIterator(){
      if(CircularLinkedList.this.n == 0){
        this.curr = null;
      }
      else{
        this.curr = CircularLinkedList.this.last.next;
      }
      this.nOp = CircularLinkedList.this.nOp;
      this.pos = 0;
    }

    @Override
    public void remove(){
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext(){
      if(nOp != CircularLinkedList.this.nOp)
        throw new ConcurrentModificationException();
      return this.pos != CircularLinkedList.this.n;
    }

    @Override
    public Item next(){
      if(nOp != CircularLinkedList.this.nOp)
        throw new ConcurrentModificationException();
      if(!hasNext())
        throw new NoSuchElementException();
      Item ret = this.curr.item;
      this.curr = this.curr.next;
      this.pos++;
      return ret;
    }
  }

  public static void main(String[] args){
    // CircularLinkedList<Integer> list = new CircularLinkedList<>();
    // for(int i = 0; i < 115; i++){
    //   list.enqueue(i);
    // }
    // Integer i;
    // Iterator<Integer> iter = list.iterator();
    // while(iter.hasNext()){
    //   i = iter.next();
    //   System.out.println(i);
    // }
    for (int j = 0; j < 20; j++) {
        CircularLinkedList<Integer> a = new CircularLinkedList<>();
        System.out.println(a.size());
        a.enqueue(j);
        System.out.println(a.size());
        Iterator itera = a.iterator();
        System.out.println(itera.hasNext()) ;
        System.out.println(itera.next());

        CircularLinkedList<Integer> b = new CircularLinkedList<>();
        b.enqueue(j);
        b.remove(0);
        Iterator iterb = b.iterator();
        System.out.println(iterb.hasNext());

    }
  }
}
