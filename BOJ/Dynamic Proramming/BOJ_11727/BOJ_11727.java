import java.util.*;

public class BOJ_11727 {

  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    System.out.println(tile2(n));
  }
  
  static int tile2(int n) {
    int dp[] = new int[1001];
    dp[0] = 1;
    dp[1] = 3;

    for(int i=2; i<n; i++) {
        dp[i] = dp[i-1] + dp[i-2] * 2;
        dp[i] %= 10007;
    }
    return dp[n-1];
  }
}
