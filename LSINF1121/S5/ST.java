package S5;

public interface ST<K extends Comparable<K>, E> {

    void put(K key, E elem);

    E get(K key);

    void delete(K key);

    default boolean contains(K key){
        return get(key) != null;
    }

    default boolean isEmpty(){
        return size() == 0;
    }

    int size();

    K min();

    K max();

    K floor(K key);

    K ceiling(K key);

    int rank(K key);

    K select(int rank);

    default void deleteMin(){
        delete(min());
    }

    default void deleteMax(){
        delete(max());
    }

    default int size(K lo, K hi){
        return rank(hi) - rank(lo);
    }

    default Iterable<K> keys(){
        return keys(min(), max());
    }

    Iterable<K> keys(K lo, K hi);
}
