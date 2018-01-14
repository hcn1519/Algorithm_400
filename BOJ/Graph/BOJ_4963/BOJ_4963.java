import java.util.*;

public class BOJ_4963 {
  static int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
  static int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};

  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    while(true) {

      int w = scin.nextInt();
      int h = scin.nextInt();

      if (w == 0 && h == 0) break;

      int m[][] = new int[h][w];
      boolean check[][] = new boolean[h][w];

      for(int i=0; i<h; i++) {
        for(int j=0; j<w; j++) {
          m[i][j] = scin.nextInt();
        }
      }

      int islandCount = 0;
      for(int i=0; i<h; i++) {
        for(int j=0; j<w; j++) {
          if (m[i][j] == 1 && check[i][j] == false) {
              islandCount++;
              dfs(m, check, i, j, h, w);
          }
        }
      }

      System.out.println(islandCount);

    }
  }

  static void dfs(int[][] m, boolean[][] check, int x, int y, int h, int w) {
    check[x][y] = true;
    for(int i=0; i<8; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (0 <= nx && nx < h && 0 <= ny && ny < w) {
        if (check[nx][ny] == false && m[nx][ny] == 1)
          dfs(m, check, nx, ny, h, w);
      }
    }

  }
}
