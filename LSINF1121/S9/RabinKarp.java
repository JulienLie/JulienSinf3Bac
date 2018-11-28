package S9;

import java.util.*;

public class RabinKarp
{
    private Map<Long, String> pat; // patterns (only needed for Las Vegas)
    //private Set<Long> patHash; // patterns hash values
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
        this.M = pat[0].length(); // O(1)
        this.pat = new HashMap<>();//M >= Math.log(pat.length) ? new TreeSet<>() : new HashSet<>(); // save pattern (only needed for Las Vegas) O(1)
        //this.patHash = new HashSet<>(); // O(1)
        Q = 4463; // O(1)
        RM = 1; // O(1)
        for (int i = 1; i <= M-1; i++) // Compute R^(M-1) % Q for use O(M)
            RM = (R * RM) % Q; // in removing leading digit. O(1)
        for(String s : pat){ // O (L)
            this.pat.put(hash(s, M), s); // O(M)
        }
        //this.pat.put(hash(pat[0], M), pat[0]);
    }


    public boolean check(int i,String txt){ // Monte Carlo (see text.)
        return this.check(i, txt, hash(txt.substring(i, i + M), M)); // O(M) ou O(log L)
    } // For Las Vegas, check pat vs txt(i..i-M+1).

    public boolean check(int i, String txt, long hash){
        return this.pat.get(hash).equals(txt.substring(i, i+M)); // O(M)
    }

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
        if (pat.containsKey(txtHash) && check(0, txt, txtHash)) return 0; // Match at beginning. O(1)
        for (int i = M; i < N; i++) // O(N)
        { // Remove leading digit, add trailing digit, check for match.
            txtHash = (txtHash + Q - RM*txt.charAt(i-M) % Q) % Q; // O(1)
            txtHash = (txtHash*R + txt.charAt(i)) % Q; // O(1)
            if (pat.containsKey(txtHash)) // O(1)
                if (check(i - M + 1,txt, txtHash)) return i - M + 1; // match O(M) ou O(log L)
        }
        return N; // no match found O(1)
    }
}