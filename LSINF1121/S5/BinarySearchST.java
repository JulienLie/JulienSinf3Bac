package S5;

public class BinarySearchST<K extends Comparable<K>, E>{

  private int size;
  private K[] keys;
  private E[] elems;
  private K lastKey;
  private int lastIndex;

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

  public boolean put(K key, E elem){
    if(elem == null) return delete(key);
    if(size == 0){
      keys[0] = key;
      elems[0] = elem;
      size++;
      return true;
    }
    int index = findPos(key);
    if(index < keys.length && keys[index] != null && keys[index].compareTo(key) == 0){
      elems[index] = elem;
      return true;
    }
    if(size == keys.length) resize(keys.length*2);
    for(int i = index; i < size; i++){
      keys[i+1] = keys[i];
      elems[i+1] = elems[i];
    }
    keys[index] = key;
    elems[index] = elem;
    size++;
    return true;
  }

  public E get(K key){
    int index = findPos(key);
    if(keys[index].compareTo(key) == 0) return elems[index];
    else return null;
  }

  private int findPos(K key){
    if(key == this.lastKey) return lastIndex;
    if (key == null) throw new IllegalArgumentException("argument to rank() is null");

        int lo = 0, hi = size-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
  }

  public boolean delete(K key){
    int index = findPos(key);
    if(keys[index].compareTo(key) != 0) return false;
    for(int i = index; i < size-1; i++ /* Partie de Clement*/){
      keys[i] = keys[i+1];
      elems[i] = elems[i+1];
    }
    size--;
    if(this.size < keys.length/2) resize(size);
    return true;
  }

  public K floor(K key){
    int pos = this.findPos(key);
    return keys[pos] == key ? keys[pos] : keys[pos-1];
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
