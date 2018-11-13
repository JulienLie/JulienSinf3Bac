package S9;

public class IncrementalHash {

  private static final int R = 31;
  private int M;
  private int RM;
  private int Q;

  /**
   *
   * @param Q is the modulo to apply
   * @param M is the length of the words to hash
   */
  public IncrementalHash(int Q, int M) {
      assert(M > 0);
      assert(Q > 0);
      this.Q = Q;
      this.M = M;
      // computes (R^(M-1))%Q
      // which might be useful for implementing nextHash
      RM = 1;
      for (int i = 1; i <= M-1; i++) {
          RM = (RM * R)%Q;
      }
  }

  /**
   * Compute the hash function on the substring
   * t[from,...,from+M-1] in O(1)
   * @param t
   * @param previousHash = hash(from-1)
   * @param 0 < from <= t.length-M
   * @return (t[from]*R^(M-1)+t[from+1]*R^(M-2)+...+t[from+M-1])%Q
   */
  public int nextHash(char[] t, int previousHash, int from) {
      int hash = previousHash;
      hash = (hash + Q - ((t[from-1]*RM)%Q)) % Q;
      hash = (R*hash+t[from+M-1]) % Q;
      return hash;
  }


  /**
   * Compute the hash function on the substring
   * t[from,...,from+M-1] in O(M)
   * @param t
   * @param 0 <= from <= t.length-M
   * @return (t[from]*R^(M-1)+t[from+1]*R^(M-2)+...+t[from+M-1])%Q
   */
  public int hash(char[] t, int from) {
      int h = 0;
      for (int i = from; i < from+M; i++) {
          h = (R*h+t[i]) % Q;
      }
      return h;
  }
}
