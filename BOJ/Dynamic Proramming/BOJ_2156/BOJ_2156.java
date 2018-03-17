import java.util.*;

public class BOJ_2156 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int matrix[] = new int[n+1];

    for (int i=1; i<n+1; i++) {
      matrix[i] = scin.nextInt();
    }

    int dp[][] = new int[n+1][3];

    for (int i=1; i<n+1; i++) {
        for (int j=0; j<3; j++) {
          if (i == 1) {
            dp[i][1] = matrix[i];
            dp[i][2] = matrix[i];
            continue;
          }

          if (j == 0) {
            int temp = max(dp[i-1][0], dp[i-1][1]);
            dp[i][0] = max(temp, dp[i-1][2]);

          } else if (j == 1) {
            dp[i][1] = dp[i-1][0] + matrix[i];
          } else {
            dp[i][2] = dp[i-1][1] + matrix[i];
          }

      }
    }

    int temp = max(dp[n][0], dp[n][1]);
    int result = max(temp, dp[n][2]);
    System.out.println(result);
  }

  static int max(int a, int b) {
    return a > b ? a : b;
  }
}
