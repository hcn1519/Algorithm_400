import java.util.*;

public class BOJ_1890 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();

    long e[][] = new long [n+1][n+1];

    for (int i=1; i<=n; i++) {
      for (int j=1; j<=n; j++) {
        e[i][j] = scin.nextInt();
      }
    }

    long dp[][] = new long [n+1][n+1];

    dp[1][1] = 1;
    for (int i=1; i<=n; i++) {
      for (int j=1; j<=n; j++) {
        long jump = e[i][j];

        if (jump != 0) {
          if (i + jump <= n) {
            int temp = (int)i + (int)jump;
            dp[temp][j] += dp[i][j];
          }
          if (j + jump <= n) {
            int temp = (int)j + (int)jump;
            dp[i][temp] += dp[i][j];
          }
        }
      }
    }
    System.out.println(dp[n][n]);

    for (int i=1; i<=n; i++) {
      for (int j=1; j<=n; j++) {
        System.out.print(dp[i][j] + " ");

      }
      System.out.println();
    }
  }
}
