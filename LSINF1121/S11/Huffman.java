package S11;

import java.util.TreeSet;

public class Huffman {
    private Huffman() { }

    // Huffman trie node
    public static class Node implements Comparable<Node>{
        public final int ch;
        public final int freq;
        public final Node left, right;

        Node(int ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            return this.freq - o.freq;
        }

        // is the node a leaf node?
        public boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }
    }

    /**
     * build the Huffman trie given frequencies
     * corresponding to each character codes from 0 to R-1.
     * freq[i] is the frequency for the character with code i
     * freq.length = R.
     * R is either 256 or 65536, depending on whether the user choose to use unicode or ASCII.
     */
    public static Node buildTrie(int R, int[] freq) {
        TreeSet<Node> all = new TreeSet<>((node, t1) -> {
            int comp = Integer.compare(node.freq, t1.freq);
            if(comp == 0) return Integer.compare(node.ch, t1.ch);
            else return comp;
        });
        for(int i = 0; i < R; i++){
            if(freq[i] != 0) all.add(new Node(i, freq[i], null, null));
        }
        while(all.size() > 1) {
            Node low1 = all.pollFirst();
            Node low2 = all.pollFirst();
            Node sum = new Node(-1, low1.freq+low2.freq, low1, low2);
            all.add(sum);
        }
        return all.pollFirst();
    }
}
