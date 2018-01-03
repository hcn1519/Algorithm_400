import java.util.*;

public class BOJ_9095 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);
    int n = scin.nextInt();

    int result[] = sumOfCase();

    for(int i =1; i<=n; i++) {
        int input = scin.nextInt();
        System.out.println(result[input]);
    }
  }
  static int[] sumOfCase() {
    int dp[] = new int[11];
    dp[0] = 1;

    for(int i=1; i<=10; i++) {
      for(int j=1; j<=3; j++) {
        if (i-j >= 0)
          dp[i] += dp[i-j];
      }
    }

    return dp;
  }
}
