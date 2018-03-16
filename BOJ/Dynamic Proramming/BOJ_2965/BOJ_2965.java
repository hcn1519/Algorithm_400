import java.util.*;

public class BOJ_2965 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);
    int x = scin.nextInt();
    int y = scin.nextInt();
    int z = scin.nextInt();

    int dp[][][] = new int[101][101][101];

    int result = jump(dp, x, y, z);

    System.out.println(result);
  }

  static int jump(int dp[][][],int x, int y, int z) {
    if (x + 1 == y && y + 1 == z) {
      return 0;
    }

    if (dp[x][y][z] != 0) {
      return dp[x][y][z];
    }
    int gap1 = y-x;
    int gap2 = z-y;

    if (gap1 == 1) {
      dp[x][y][z] = jump(dp, y, y+1, z) + 1;
    }

    if (gap2 == 1) {
      dp[x][y][z] = jump(dp, x, y-1, y) + 1;
    }

    if (gap1 <= gap2) {
      dp[x][y][z] = jump(dp, y, y+1, z) + 1;
    } else {
      dp[x][y][z] = jump(dp, x, x+1, y) + 1;
    }

    return dp[x][y][z];
  }

}
