import java.util.*;

public class BOJ_9465 {
  static int getMaximumSticker(int matrix[][], int n) {

      int dp[][] = new int[3][n+1];

      // 열 단위로 루프를 돌아야 한다.
      for (int j=1; j<n+1; j++) {
          for (int i=0; i<3; i++) {
            if (i == 0) {
              int temp = max(dp[0][j-1], dp[1][j-1]);
              dp[i][j] = max(temp, dp[2][j-1]);
            } else if (i == 1) {
              int temp = max(dp[0][j-1], dp[2][j-1]);
              dp[i][j] = temp + matrix[i-1][j];
            } else {
              int temp = max(dp[0][j-1], dp[1][j-1]);
              dp[i][j] = temp + matrix[i-1][j];
            }
          }
      }
      int temp = max(dp[0][n], dp[1][n]);
      return max(temp, dp[2][n]);
  }

  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int testTime = scin.nextInt();

    for (int x=0; x<testTime; x++) {
      int n = scin.nextInt();
      int matrix[][] = new int[2][n+1];

      for (int i=0; i<2; i++) {
          for (int j=1; j<n+1; j++) {
            matrix[i][j] = scin.nextInt();
          }
      }

      int result = getMaximumSticker(matrix, n);
      System.out.println(result);
    }

  }

  static int max(int a, int b) {
    return a > b ? a : b;
  }
}
