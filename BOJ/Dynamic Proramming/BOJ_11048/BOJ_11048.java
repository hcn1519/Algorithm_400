import java.util.*;

public class BOJ_11048 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int m = scin.nextInt();

    int e[][] = new int[n+1][m+1];

    for (int i=1; i<=n; i++) {
      for (int j=1; j<=m; j++) {
        e[i][j] = scin.nextInt();
      }
    }

    int dp[][] = new int[n+1][m+1];

    for (int i=1; i<=n; i++) {
      for (int j=1; j<=m; j++) {
        dp[i][j] = max3(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + e[i][j];
      }
    }
    System.out.println(dp[n][m]);

  }

  static int max3(int a, int b, int c) {
    return max(max(a, b), c);
  }

  static int max(int a, int b) {
    return a > b ? a : b;
  }
}
