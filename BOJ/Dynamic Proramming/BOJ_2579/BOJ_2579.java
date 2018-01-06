import java.util.*;

public class BOJ_2579 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int score[] = new int[n+1];

    for(int i=1; i<=n; i++){
      score[i] = scin.nextInt();
    }

    System.out.println(maxScore(score, n));
  }
  static int maxScore(int score[], int n) {
    int dp[] = new int[n+1];

    if (n == 1) {
      return score[1];
    } else if (n == 2) {
      return score[1] + score[2];
    }
    dp[1] = score[1];
    dp[2] = score[1] + score[2];

    for(int i=3; i<=n; i++) {
      if (dp[i-2] > dp[i-3] + score[i-1]) {
          dp[i] = dp[i-2];
      } else {
          dp[i] = dp[i-3] + score[i-1];
      }
      dp[i] += score[i];
    }

    return dp[n];
  }

  static int max(int a[]) {
    int max = a[0];
    for(int i=1; i<a.length; i++) {
      if (max < a[i]) {
        max = a[i];
      }
    }
    return max;
  }
}
