import java.util.*;

public class KMP {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    String p = scin.next();
    int[] pi = preProcessing(p);

    for(int i=0; i<p.length(); i++) {
      System.out.print(pi[i] + " ");
    }
    System.out.println();
  }

  static int[] preProcessing(String p) {
    int m = p.length();
    int[] pi = new int[m];
    int j = 0;

    for(int i=1; i<m; i++) {
      while(j>0 && p.charAt(i) != p.charAt(j)) {
        j = pi[j-1];
      }

      if (p.charAt(i) == p.charAt(j)) {
        pi[i] = j+1;
        j += 1;
      } else {
        pi[i] = 0;
      }

    }

    return pi;
  }
}
