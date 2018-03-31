import java.util.*;

public class BOJ_2293 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int k = scin.nextInt();

    int coins[] = new int[n];

    for (int i=0; i<n; i++) {
      coins[i] = scin.nextInt();
    }

    int dp[] = new int[k+1];
    dp[0] = 1;

    for (int j=0; j<n; j++) {
        for (int i=1; i<=k; i++) {
            if (i-coins[j] >= 0) {
                dp[i] += dp[i-coins[j]];
            }
        }
    }

    System.out.println(dp[k]);

  }
}
