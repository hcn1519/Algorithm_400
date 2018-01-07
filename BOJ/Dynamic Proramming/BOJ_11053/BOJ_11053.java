import java.util.*;

public class BOJ_11053 {
  public static void main(String[]args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int nums[] = new int[n];

    for(int i=0; i<n; i++) {
        nums[i] = scin.nextInt();
    }

    int dp[] = new int[n];

    for(int i=0; i<n; i++) {
      dp[i] = 1;
      for(int j=0; j<i; j++) {
        if(nums[j] < nums[i] && dp[i] < dp[j] + 1) {
            dp[i] = dp[j] + 1;
        }
      }
    }

    int ans = dp[0];
    for(int i=0; i<n; i++) {
      if (dp[i] > ans) {
        ans = dp[i];
      }
    }

    System.out.println(ans);
  }
}
