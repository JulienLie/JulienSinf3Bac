package S7;

public class RedBlackTreeST<K extends Comparable<K>, E> extends BinarySearchTreeST<K, E>{

  public RedBlackTreeST(){
    super();
  }

//  public void put(K key, E elem){
//    if(root == null){
//      root = new Node();
//      root.key = key;
//      root.elem = elem;
//    }
//  }

  // public V get(K key){
  //   get(key, root);
  // }
  //
  // private V get(K key, Node n){
  //   if(n == null) return null;
  //   if(n.key == key) return n.val;
  //   if(key > n.key) return get(key, n.right);
  //   if(key < n.key) return get(key, n.left);
  //   return null;
  // }
  //
  // public void delete(K key){
  //   return;
  // }
  //
  // public int size(){
  //   return size;
  // }
  //
  // public K min(){
  //   return min(root);
  // }
  //
  // private K min(Node n){
  //   if(n.left == null) return n;
  //   return n.left;
  // }
  //
  // public K max(){
  //   return max(root);
  // }
  //
  // private K max(Node n){
  //   if(n.right == null) return n;
  //   return max(n.right);
  // }
  //
  // public K floor(K key){
  //   if(key == null) throw new IllegalArgumentException();
  //   return floor(key, root);
  // }
  //
  // private K floor(K key, Node n){
  //   if(n == null) return null;
  //   if(key < n.key) return ceil(key, n.left);
  //   if(key == n.key) return n.key;
  //   if(key > root.key){
  //     K temp = ceil(key, n.left);
  //     if(temp == null) return root.key;
  //     else return temp;
  //   }
  //   return null;
  // }
  //
  // public K ceiling(K key){
  //   if(key == null) throw new IllegalArgumentException();
  //   return ceiling(key, root);
  // }
  //
  // private K ceiling(Node root, K key){
  //   if(root == null) return null;
  //   if(key > root.key) return ceil(root.right, key);
  //   if(key == root.key) return root.key;
  //   if(key < root.key){
  //       K temp = ceil(root.left, key);
  //       if(temp == null) return root.key;
  //       else return temp;
  //   }
  //   return null;
  // }
  //
  // public int rank(K key){
  //   if(key == null) throw new IllegalArgumentException();
  //   return rank(key, root);
  // }
  //
  // private int rank(K key, Node n){
  //   if(n == null) return 0;
  //   int cmp = key.compareTo(n.key);
  //   if(cmp < 0) return rank(n.right, key);
  //   if(cmp > 0) return 1 + size(x.left) + rank(x.right, key);
  // }
  //
  // public K select(int rank){
  //
  // }
  //
  // public Iterable<K> keys(K lo, K hi){
  //
  // }

  public static void main(String[] args){
    RedBlackTreeST<Integer, String> rb = new RedBlackTreeST<>();
    rb.put(0, "julien");
    System.out.println(rb.get(0));
  }
}
