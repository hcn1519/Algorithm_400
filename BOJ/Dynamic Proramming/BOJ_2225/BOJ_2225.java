import java.util.*;

public class BOJ_2225 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int k = scin.nextInt();

    long dp[][] = new long[201][201];

    for (int i=1; i<=200; i++) {

        for (int j=0; j<=200; j++) {
            if (i == 1 || j == 0) {
              dp[i][j] = 1;
            } else {
                long sum = 0;
                for (int x = 0; x<=j; x++) {
                    sum += (dp[i-1][j-x]);
                    sum %= 1000000000;
                }
                dp[i][j] = sum;
            }
        }
    }
    System.out.println(dp[k][n]);
  }
}
