package S9;

import java.util.Arrays;
import java.util.HashSet;

public class RabinKarp
{
    private HashSet<String> pat; // patterns (only needed for Las Vegas)
    private HashSet<Long> patHash; // patterns hash values
    private int M; // pattern length
    private long Q; // a large prime
    private int R = 2048; // alphabet size
    private long RM; // R^(M-1) % Q

    public RabinKarp(String pat)
    {
        this(new String[]{pat});
    }

    public RabinKarp(String[] pat)
    {
        this.pat = new HashSet<>(); // save pattern (only needed for Las Vegas)
        this.patHash = new HashSet<>();
        this.M = pat[0].length();
        Q = 4463;
        RM = 1;
        for (int i = 1; i <= M-1; i++) // Compute R^(M-1) % Q for use
            RM = (R * RM) % Q; // in removing leading digit.
        for(String aPat : pat) {
            this.pat.add(aPat);
            this.patHash.add(hash(aPat, M));
        }
    }


    public boolean check(int i,String txt){ // Monte Carlo (see text.)
        return this.pat.contains(txt.substring(i, i + M)); // O(M)
    } // For Las Vegas, check pat vs txt(i..i-M+1).

    private long hash(String key, int M){ // Compute hash for key[0..M-1].
        long h = 0;
        for (int j = 0; j < M; j++)
            h = (R * h + key.charAt(j)) % Q;
        return h;
    }


    public int search(String txt) // O(N*M)
    { // Search for hash match in text
        int N = txt.length(); // O(1)
        long txtHash = hash(txt, M); // O(M)
        if (patHash.contains(txtHash)) return 0; // Match at beginning. O(1)
        for (int i = M; i < N; i++) // O(N)
        { // Remove leading digit, add trailing digit, check for match.
            txtHash = (txtHash + Q - RM*txt.charAt(i-M) % Q) % Q; // O(1)
            txtHash = (txtHash*R + txt.charAt(i)) % Q; // O(1)
            if (patHash.contains(txtHash)) // O(1)
                if (check(i - M + 1,txt)) return i - M + 1; // match O(M)
        }
        return N; // no match found O(1)
    }
}