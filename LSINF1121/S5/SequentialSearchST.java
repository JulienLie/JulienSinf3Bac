import java.util.LinkedList;
import java.util.List;

public class SequentialSearchST<K extends Comparable<K>, E> implements ST<K, E>{

    protected class Node{
        K key;
        E elem;
        Node next;

        Node(K key, E elem){
            this.key = key;
            this.elem = elem;
        }
    }

    protected Node head;
    protected int size;
    protected K lastKey;
    protected int lastIndex;

    public SequentialSearchST(){
        this.head = null;
        this.size = 0;
        this.lastKey = null;
        this.lastIndex = -1;
    }

    @Override
    public void put(K key, E elem) {
        if(elem == null) delete(key);
        if(this.head == null) this.head = new Node(key, elem);
        else{
            Node cur = head;
            while(cur.next != null) cur = cur.next;
            cur.next = new Node(key, elem);
        }
        size++;
    }

    @Override
    public E get(K key) {
        if(key == null || this.isEmpty()) return null;
        Node cur = head;
        while(cur.next != null){
            if(cur.key == key) return cur.elem;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public void delete(K key) {
        if(key == null || this.isEmpty()) return;
        if(key == head.key){
            head = head.next;
            size--;
            return;
        }
        Node cur = head;
        Node prec = head;
        while(cur.next != null) {
            if (cur.key == key) {
                prec.next = cur.next;
                size--;
                return;
            }
            prec = cur;
            cur = cur.next;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public K min() {
        return head == null ? null : head.key;
    }

    @Override
    public K max() {
        if(head == null) return null;
        Node last = head;
        while(last.next != null) last = last.next;
        return last.key;
    }

    @Override
    public K floor(K key) {
        if(head == null) return null;
        Node prec = head;
        Node next = head;
        while(next.next != null){
            int cmp = next.key.compareTo(key);
            if(next.key.compareTo(key) == 0) return next.key;
            else if(cmp > 0) return prec.key;
            prec = next;
            next = next.next;
        }
        return next.key;
    }

    @Override
    public K ceiling(K key) {
        if(head == null) return null;
        Node next = head;
        while(next.next != null){
            if(next.key.compareTo(key) >= 0) return next.key;
            next = next.next;
        }
        return next.key;
    }

    @Override
    public int rank(K key) {
        if(head == null) return -1;
        Node cur = head;
        for(int i = 0; cur.next != null; i++){
            if(cur.key.compareTo(key) >= 0) return i;
            cur = cur.next;
        }
        return size-1;
    }

    @Override
    public K select(int rank) {
        if(rank >= size) throw new IndexOutOfBoundsException();
        Node cur = head;
        for(int i = 0; i < rank; i++) cur = cur.next;
        return cur.key;
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        List<K> ret = new LinkedList<>();
        Node cur = head;
        int rankLo = rank(lo);
        int rankHi = rank(hi);
        for(int i = 0; i < rankLo; i++) cur = cur.next;
        for(int i = 0; i < rankHi; i++){
            ret.add(cur.key);
            cur = cur.next;
        }
        return ret;
    }
}
