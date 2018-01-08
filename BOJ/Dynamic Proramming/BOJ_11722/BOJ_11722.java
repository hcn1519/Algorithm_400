import java.util.*;

public class BOJ_11722 {

  static void printArray(int[] dp) {
    for(int i=0; i<dp.length; i++) {
      System.out.print(dp[i]);
      System.out.print(" ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int nums[] = new int[n];

    for(int i=0; i<n; i++) {
        nums[i] = scin.nextInt();
    }

    int dp[] = new int[n];

    for(int i = 0; i < nums.length / 2; i++) {
        int temp = nums[i];
        nums[i] = nums[nums.length - i - 1];
        nums[nums.length - i - 1] = temp;
    }

    // dp[i] = max(dp[j]) + 1;
    // (i > j)
    // (nums[i] > nums[j])
    for(int i=0; i<n; i++) {
      dp[i] = 1;
      for(int j=0; j<i; j++) {
        if (nums[i] > nums[j] && dp[i] < dp[j] + 1) {
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
