import java.util.*;

public class BOJ_11057 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();

    System.out.println(ascendingN(n));
  }
  static long ascendingN(int n) {
    int dp[][] = new int[n+1][10];
    int mod = 10007;

    for(int i=0; i<=9; i++) {
      dp[1][i] = 1;
    }

    for(int i=1; i<=n; i++) {
      for(int j=0; j<=9; j++) {
        for(int k=0; k<=j; k++) {
            dp[i][j] += dp[i-1][k];

            dp[i][j] %= mod;
        }
      }
    }

    long ans = 0;
    for(int i=0; i<=9; i++){
      ans += dp[n][i];
    }
    return ans % mod;
  }
}
