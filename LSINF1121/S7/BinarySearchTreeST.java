package S7;

import S5.ST;

import java.util.List;
import java.util.LinkedList;

public class BinarySearchTreeST<K extends Comparable<K>, E> implements ST<K, E> {

  private class Node{
    K key;
    E elem;
    Node left;
    Node right;

    Node(K key, E elem){
      this.key = key;
      this.elem = elem;
      this.left = null;
      this.right = null;
    }
  }

  protected Node root;
  protected int size;

  public BinarySearchTreeST(){
    this.root = null;
    this.size = 0;
  }

  @Override
  public void put(K key, E elem){
    if(elem == null) delete(key);
    if(root == null){
      this.root = new Node(key, elem);
    }
    else{
      Node cur = root;
      boolean pos = false;
      while(!pos){
        int cmp = cur.key.compareTo(key);
        if(cmp == 0){
          cur.elem = elem;
          pos = true;
        }
        else if(cmp > 0){
          if(cur.right != null) cur = cur.right;
          else{
            cur.right = new Node(key, elem);
            pos = true;
          }
        }
        else if(cmp < 0){
          if(cur.left != null) cur = cur.left;
          else{
            cur.left = new Node(key, elem);
            pos = true;
          }
        }
      }
    }
    size++;
  }

  @Override
  public E get(K key){
    if(root == null) return null;

    Node cur = root;
    while(true){
      int cmp = cur.key.compareTo(key);
      if(cmp == 0) return cur.elem;
      else if(cmp > 0)
        if(cur.right != null) cur = cur.right;
        else return null;
      else if(cmp < 0)
        if(cur.left != null) cur = cur.left;
        else return null;
    }
  }

  @Override
  public void delete(K key){
    root = delete(root, key);
    size = size == 0 ? 0 : size-1;
  }

  private Node delete(Node x, K key){
    if(x == null) return null;
    int cmp = key.compareTo(x.key);
    if(cmp < 0) x.left = delete(x.left, key);
    if(cmp > 0) x.right = delete(x.right, key);
    else{
      if(x.right == null) return x.left;
      if(x.left == null) return x.right;
      Node t = x;
      x = min(t.right);
      x.right = deleteMin(t.right);
      x.left = t.left;
    }
    return x;
  }

  @Override
  public int size(){
    return size;
  }

  private int size(Node n){
    int sum = 0;
    if(n.left != null) sum += size(n.left);
    if(n.right != null) sum += size(n.right);
    return sum+1;
  }

  @Override
  public K min(){
    return min(root).key;
  }

  private Node min(Node x){
    if(x.left == null) return x;
    return min(x.left);
  }

  @Override
  public void deleteMin(){
    root = deleteMin(root);
    size = root == null ? 0 : size-1;
  }

  private Node deleteMin(Node x){
    if(x.left == null) return x.right;
    x.left = deleteMin(x.left);
    return x;
  }

  @Override
  public K max(){
    return max(root).key;
  }

  private Node max(Node x){
    if(x.right == null) return x;
    return max(x.right);
  }

  @Override
  public void deleteMax(){
    root = deleteMax(root);
    size = root == null ? 0 : size-1;
  }

  private Node deleteMax(Node x){
    if(x.right == null) return x.left;
    x.right = deleteMin(x.right);
    return x;
  }

  @Override
  public K floor(K key){
    Node f = floor(root, key);
    return f == null ? null : f.key;
  }

  private Node floor(Node x, K key){
    if(x == null) return null;
    int cmp = key.compareTo(x.key);
    if(cmp == 0) return x;
    else if(cmp < 0) return floor(x.left, key);
    Node t = floor(x.right, key);
    return t != null ? t : x;
  }

  @Override
  public K ceiling(K key){
    if(root == null) return null;
    Node c = ceiling(root, key);
    return c == null ? null : c.key;
  }

  private Node ceiling(Node x, K key){
    if(x == null) return null;
    int cmp = key.compareTo(x.key);
    if(cmp == 0) return x;
    else if(cmp > 0) return ceiling(x.right, key);
    Node t = ceiling(x.left, key);
    return t != null ? t : x;
  }

  @Override
  public int rank(K key){
    return rank(root, key);
  }

  private int rank(Node x, K key){
    if(x == null) return 0;
    int cmp = key.compareTo(x.key);
    if(cmp < 0) return rank(x.right, key);
    else if(cmp > 0) return 1 + size(x.left) + rank(x.right, key);
    else return size(x.left);
  }

  @Override
  public K select(int rank){
    return select(root, rank).key;
  }

  private Node select(Node x, int rank){
    if(x == null) return null;
    int t = size(x.left);
    if(t > rank) return select(x.left, rank);
    else if (t < rank) return select(x.right, rank-t-1);
    else return x;
  }

  @Override
  public Iterable<K> keys(K lo, K hi){
    List<K> queue = new LinkedList<>();
    keys(root, queue, lo, hi);
    return queue;
  }

  private void keys(Node x, List<K> q, K lo, K hi){
    if(x == null) return;
    int cmplo = lo.compareTo(x.key);
    int cmphi = hi.compareTo(x.key);
    if(cmplo < 0) keys(x.left, q, lo, hi);
    if(cmplo <= 0 && cmphi >= 0) q.add(x.key);
    if(cmphi > 0) keys(x.right, q, lo, hi);
  }
}
