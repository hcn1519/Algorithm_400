import java.util.*;

public class BOJ_10844 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();

    System.out.println(stairN(n));
  }

  static long stairN(int n) {
    long dp[][] = new long[n+1][10];
    long mod = 1000000000;

    for(int i = 1; i<=9; i++) {
      dp[1][i] = 1;
    }

    for(int i=2; i<=n; i++) {
      for(int k=0; k<10; k++) {

        dp[i][k] = 0;

        if (k-1 >=0) {
          dp[i][k] += dp[i-1][k-1];
        }

        if (k+1 <= 9) {
          dp[i][k] += dp[i-1][k+1];
        }
        dp[i][k] %= mod;
      }
    }

    long ans = 0;
    for(int i=0; i<=9; i++){
        ans += dp[n][i];
    }
    return ans % mod;
  }
}
