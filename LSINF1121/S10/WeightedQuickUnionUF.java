package S10;

public class WeightedQuickUnionUF {
    private int[] id;
    private int[] sz;
    private int count;

    public WeightedQuickUnionUF(int size){
        this.count = size;
        id = new int[size];
        sz = new int[size];
        for(int i = 0; i < size; i++){
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int count(){
        return count;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    public int find(int p){
        while(p != id[p]) p = id[p];
        return p;
    }

    public void union(int p, int q){
        int i = find(p);
        int j = find(q);
        if(i==j) return;
        if(sz[i] > sz[j]){
            id[i] = j;
            sz[j] += sz[i];
        }
        else{
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for(int i : id){
            ret.append(i).append(",");
        }
        ret.deleteCharAt(ret.length()-1);
        return ret.toString();
    }

    public static void main(String[] args){
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(10);
        uf.union(9, 1);
        System.out.println(uf);
        uf.union(8, 3);
        System.out.println(uf);
        uf.union(0, 1);
        System.out.println(uf);
//        uf.union(7, 0);
//        System.out.println(uf);
//        uf.union(1, 2);
//        System.out.println(uf);
//        uf.union(8, 4);
//        System.out.println(uf);
//        uf.union(6, 5);
//        System.out.println(uf);
//        uf.union(1, 7);
//        System.out.println(uf);
//        uf.union(6, 0);
//        System.out.println(uf);
    }
}
