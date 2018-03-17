import java.util.*;

public class BOJ_11054 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();

    int matrix[] = new int [n+1];

    for (int i=1; i<n+1; i++) {
      matrix[i] = scin.nextInt();
    }

    int dp1[] = new int [n+1];

    for (int i=1; i<n+1; i++) {
      dp1[i] = 1;
      for (int j=1; j<i; j++) {
        if (matrix[i] > matrix[j] && dp1[i] < dp1[j] + 1) {
          dp1[i] = dp1[j] + 1;
        }
      }
    }

    int dp2[] = new int [n+1];

    for (int i=n; i>=1; i--) {
      dp2[i] = 1;
      for (int j=i+1; j<n+1; j++) {
        if (matrix[i] > matrix[j] && dp2[i] < dp2[j] + 1) {
          dp2[i] = dp2[j] + 1;
        }
      }
    }

    int max = dp1[1] + dp2[1] - 1;
    for (int i=1; i<n+1; i++) {
      if (max < dp1[i] + dp2[i] - 1) {
        max = dp1[i] + dp2[i] - 1;
      }
    }

    System.out.println(max);
  }
}
