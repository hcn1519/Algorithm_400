import java.util.*;

public class BOJ_2133 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();

    int dp[] = new int[n+1];
    dp[0] = 1;

    for (int i=2; i<=n; i+=2) {
      dp[i] = dp[i-2] * 3;
      for (int j=i-4; j>=0; j-=2) {
        dp[i] += (dp[j] * 2);
      }
    }

    System.out.println(dp[n]);

  }
}
