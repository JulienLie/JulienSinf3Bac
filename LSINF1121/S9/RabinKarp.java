package S9;

public class RabinKarp
{
    private String pat[]; // pattern (only needed for Las Vegas)
    private long patHash[]; // pattern hash value
    private int M; // pattern length
    private long Q; // a large prime
    private int R = 2048; // alphabet size
    private long RM; // R^(M-1) % Q

    public RabinKarp(String pat)
    {
        this.pat = new String[]{pat}; // save pattern (only needed for Las Vegas)
        this.M = this.pat[0].length();
        Q = 4463;
        RM = 1;
        for (int i = 1; i <= M-1; i++) // Compute R^(M-1) % Q for use
            RM = (R * RM) % Q; // in removing leading digit.
        patHash = new long[]{hash(pat, M)};
    }

    public RabinKarp(String[] pat)
    {
        this.pat = pat; // save pattern (only needed for Las Vegas)
        this.M = pat[0].length();
        Q = 4463;
        RM = 1;
        for (int i = 1; i <= M-1; i++) // Compute R^(M-1) % Q for use
            RM = (R * RM) % Q; // in removing leading digit.
        patHash = new long[pat.length];
        for(int i = 0; i < patHash.length; i++)
            patHash[i] = hash(pat[i], M);
    }


    public boolean check(int i,String txt, int pos){ // Monte Carlo (see text)
        return pat[pos].equals(txt.substring(i, i + M));
    } // For Las Vegas, check pat vs txt(i..i-M+1).

    private long hash(String key, int M){ // Compute hash for key[0..M-1].
        long h = 0;
        for (int j = 0; j < M; j++)
            h = (R * h + key.charAt(j)) % Q;
        return h;
    }


    public int search(String txt)
    { // Search for hash match in text.
        int N = txt.length();
        long txtHash = hash(txt, M);
        for(Long l : patHash)
            if (l == txtHash) return 0; // Match at beginning.
        for (int i = M; i < N; i++)
        { // Remove leading digit, add trailing digit, check for match.
            txtHash = (txtHash + Q - RM*txt.charAt(i-M) % Q) % Q;
            txtHash = (txtHash*R + txt.charAt(i)) % Q;
            for(int j = 0; j < patHash.length; j++)
                if (patHash[j] == txtHash)
                    if (check(i - M + 1,txt, j)) return i - M + 1; // match
        }
        return N; // no match found
    }
}