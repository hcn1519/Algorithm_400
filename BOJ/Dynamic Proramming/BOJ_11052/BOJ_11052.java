import java.util.*;

public class BOJ_11052 {
  public static void main(String[] args ){
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int p[] = new int[n+1];

    for(int i=1; i<=n; i++){
        p[i] = scin.nextInt();
    }

    int dp[] = new int[n+1];

    for(int i=1; i<=n; i++) {
      for(int k=1; k<=i; k++) {
        if (p[k] + dp[i-k] > dp[i]){
            dp[i] = p[k] + dp[i-k];
        }
      }
    }
    System.out.println(dp[n]);
  }
}
