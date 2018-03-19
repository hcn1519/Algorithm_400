import java.util.*;

public class BOJ_1912 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int k[] = new int[n];

    for (int i = 0; i<n; i++) {
      k[i] = scin.nextInt();
    }

    int dp[] = new int[n];
    int sum[] = new int[n];

    dp[0] = k[0];
    sum[0] = k[0];

    if (sum[0] < 0) {
      sum[0] = 0;
    }

    for (int i =1; i<n; i++) {

        sum[i] = sum[i-1] + k[i];

        if (sum[i] < 0) {
          sum[i] = 0;
        }

        if (sum[i-1] + k[i] <= dp[i-1]) {
          dp[i] = dp[i-1];
        } else {
          dp[i] = sum[i-1] + k[i];
        }
    }

    // System.out.println("sum");
    // for (int i =0; i<n; i++) {
    //   System.out.print(sum[i] + " ");
    // }
    // System.out.println();
    //
    // System.out.println("dp");
    // for (int i =0; i<n; i++) {
    //   System.out.print(dp[i] + " ");
    // }
    // System.out.println();

    System.out.println(dp[n-1]);
  }
}
