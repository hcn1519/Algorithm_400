import java.util.*;

public class BOJ_1149 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();

    int rgb[][] = new int[n+1][3];

    for(int i=1; i<=n; i++) {
      for(int j=0; j<3; j++) {
        rgb[i][j] = scin.nextInt();
      }
    }
    int dp[][] = new int[n+1][3];

    dp[1][0] = rgb[1][0];
    dp[1][1] = rgb[1][1];
    dp[1][2] = rgb[1][2];

    for(int i=2; i<=n; i++) {
      for(int j=0; j<=2; j++) {
        if (j==0) {
          dp[i][0] = rgb[i][0] + Math.min(dp[i-1][1],dp[i-1][2]);
        } else if (j==1) {
          dp[i][1] = rgb[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
        } else if (j==2) {
          dp[i][2] = rgb[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }
      }
    }

    int ans = dp[n][0];
    for(int i=1; i<=2; i++) {
      if (ans > dp[n][i]) {
        ans = dp[n][i];
      }
    }
    System.out.println(ans);

  }
}
