public class InterpolationSearchST<K extends Double, E> extends BinarySearchST<Double, E> {

    public InterpolationSearchST(){
        super();
    }

    public InterpolationSearchST(int size){
        super(size);
    }

    @Override
    public int rank(Double key) {
        if(key.equals(this.lastKey)) return lastIndex;

        int lo = 0, hi = size-1;
        while (lo <= hi) {
            int mid = (int) ((key - keys[lo])/(keys[hi] - keys[lo]))*100;
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
}
