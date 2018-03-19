import java.util.*;

public class BOJ_1699 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);
    int n = scin.nextInt();

    int dp[] = new int[n+1];

    for(int i=1; i<n+1; i++) {
      dp[i] = i;
      for (int j=1; j*j <= i; j++) {
        if (dp[i] > dp[i-j*j]+1) {
          dp[i] = dp[i-j*j]+1;
        }
      }
    }
    System.out.println(dp[n]);
  }
}
