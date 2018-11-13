package S5;

import java.util.ArrayList;
import java.util.Arrays;

public class BinarySearchST<K extends Comparable<K>, E> implements ST<K, E>{

  protected int size;
  protected K[] keys;
  protected E[] elems;
  protected K lastKey;
  protected int lastIndex;

  public BinarySearchST(){
      this(2);
  }

  public BinarySearchST(int initCapa){
    this.size = 0;
    this.keys = (K[]) new Comparable[initCapa];
    this.elems = (E[]) new Object[initCapa];
    lastKey = null;
    int lastIndex = -1;
  }

  public int size(){
    return size;
  }

  @Override
  public K min() {
    lastIndex = 0;
    lastKey = keys[0];
    return lastKey;
  }

  @Override
  public K max() {
    lastIndex = size-1;
    lastKey = keys[size-1];
    return lastKey;
  }

  public void put(K key, E elem){
    if(elem == null) delete(key);
    if(size == 0){
      keys[0] = key;
      elems[0] = elem;
      size++;
      return;
    }
    int index = rank(key);
    if(index < keys.length && keys[index] != null && keys[index].compareTo(key) == 0){
      elems[index] = elem;
      return;
    }
    if(size == keys.length) resize(keys.length*2);
    for(int i = index; i < size; i++){
      keys[i+1] = keys[i];
      elems[i+1] = elems[i];
    }
    keys[index] = key;
    elems[index] = elem;
    size++;
  }

  public E get(K key){
    int index = rank(key);
    if(keys[index].compareTo(key) == 0) return elems[index];
    else return null;
  }

  public int rank(K key){
    if(key == this.lastKey) return lastIndex;
    if (key == null) throw new IllegalArgumentException("argument to rank() is null");

        int lo = 0, hi = size-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else{
              lastIndex = mid;
              lastKey = key;
              return mid;
            }
        }
        lastIndex = lo;
        lastKey = key;
        return lo;
  }

  public void delete(K key){
    int index = rank(key);
    if(keys[index].compareTo(key) != 0) return;
    for(int i = index; i < size-1; i++ /* Partie de Clement*/){
      keys[i] = keys[i+1];
      elems[i] = elems[i+1];
    }
    size--;
    if(this.size < keys.length/2) resize(size);
  }

  public K floor(K key){
    int pos = this.rank(key);
    return keys[pos] == key ? keys[pos] : keys[pos-1];
  }

  @Override
  public K ceiling(K key) {
    int pos = this.rank(key);
    return keys[pos];
  }

  @Override
  public K select(int rank) {
    return keys[rank];
  }

  @Override
  public Iterable<K> keys(K lo, K hi) {
    return new ArrayList<>(Arrays.asList(this.keys).subList(rank(lo), rank(hi)));
  }

  private void resize(int size){
    if(this.keys.length == size) return;
    K[] bufK = (K[]) new Comparable[size];
    E[] bufE = (E[]) new Object[size];
    int min = Math.min(this.size, size);
    for(int i = 0; i < min; i++){
      bufK[i] = keys[i];
      bufE[i] = elems[i];
    }
    this.keys = bufK;
    this.elems = bufE;
  }

  @Override
  public String toString(){
    String out = "";
    for(int i = 0; i < size; i++){
      out += String.format("[%s, %s]", keys[i], elems[i]);
    }
    return out;
  }
}
