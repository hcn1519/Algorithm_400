import java.util.*;

public class BOJ_1932 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int size = scin.nextInt();

    int matrix[][] = new int[size+1][size+1];

    for (int i=1; i<=size; i++) {
      for (int k=1; k<=i; k++) {
          matrix[i][k] = scin.nextInt();
      }
    }

    int dp[][] = new int[size+1][size+1];

    for (int i=1; i<=size; i++) {
      for (int k=1; k<=i; k++) {
        if (k == 1) {
            dp[i][k] = matrix[i][k] + dp[i-1][k];
        } else if (i == k) {
            dp[i][k] = matrix[i][k] + dp[i-1][k-1];
        } else {
            dp[i][k] = matrix[i][k] + max(dp[i-1][k], dp[i-1][k-1]);
        }
      }
    }

    int max = dp[size][1];
    for (int i=2; i<=size; i++) {
        if (dp[size][i] > max) {
          max = dp[size][i];
        }
    }
    System.out.println(max);
  }
  static int max(int a, int b) {
    return a > b ? a : b;
  }
}
