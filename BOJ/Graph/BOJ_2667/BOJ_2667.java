import java.util.*;

public class BOJ_2667 {

  static int[] dx = {0, 0, 1, -1};
  static int[] dy = {1, -1, 0, 0};
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();

    int m[][] = new int[n][n];

    scin.nextLine();
    for(int i=0; i<n; i++) {
      String line = scin.nextLine();
      for(int j=0; j<line.length(); j++) {
        char c = line.charAt(j);
        m[i][j] = Character.getNumericValue(c);
      }
    }

    int cnt = 0;
    int d[][] = new int[n][n];
    for(int i=0; i<n; i++) {
      for(int j=0; j<n; j++) {
        if(m[i][j] == 1 && d[i][j] == 0) {
          cnt++;
          dfs(m, d, i, j, n, cnt);
        }
      }
    }

    int a[] = new int[cnt];
    for(int i=0; i<n; i++) {
      for(int j=0; j<n; j++) {
        if(d[i][j] != 0) {
          a[d[i][j] - 1] += 1;
        }
      }
    }

    Arrays.sort(a);
    System.out.println(cnt);
    for(int i=0; i<cnt; i++) {
      System.out.println(a[i]);
    }
  }

  static void dfs(int [][] m, int [][] d, int x, int y, int n, int cnt) {
    d[x][y] = cnt;
    for(int i=0; i<4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if (0 <= nx && nx < n && 0 <= ny && ny < n) {
        if(m[nx][ny] == 1 && d[nx][ny] == 0) {
          dfs(m, d, nx, ny, n, cnt);
        }
      }
    }
  }
}
