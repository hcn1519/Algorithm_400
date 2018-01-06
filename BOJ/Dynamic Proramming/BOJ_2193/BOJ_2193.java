import java.util.*;

public class BOJ_2193 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();

    System.out.println(specialBinaryNum(n));
  }
  static long specialBinaryNum(int n) {
    long dp[][] = new long[n+1][2];

    dp[1][1] = 1;

    for(int i=2; i<=n; i++) {
      for(int k=0; k<2; k++) {
        if (k == 0) {
          dp[i][k] = dp[i-1][0] + dp[i-1][1];
        } else {
          dp[i][k] = dp[i-1][0];
        }
      }
    }

    long ans = dp[n][0] + dp[n][1];
    return ans;
  }
}
