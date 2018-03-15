import java.util.*;

public class BOJ_1463 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int dp[] = new int[n+1];

    for (int i=2; i<=n; i++) {
      dp[i] = dp[i-1] + 1;

      if (i % 3 == 0) {
          if (dp[i/3] + 1 < dp[i]) {
              dp[i] = dp[i/3] + 1;
          }
      }

      if (i % 2 == 0) {
          if (dp[i/2] + 1 < dp[i]) {
              dp[i] = dp[i/2] + 1;
          }
      }
    }

    System.out.println(dp[n]);

  }

}
