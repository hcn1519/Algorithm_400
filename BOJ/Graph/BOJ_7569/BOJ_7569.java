import java.util.*;

class Index {
  int h;
  int x;
  int y;
  Index(int h, int x, int y) {
    this.h = h;
    this.x = x;
    this.y = y;
  }
}

public class BOJ_7569 {
  static int dh[] = {0, 0, 0, 0, 1, -1};
  static int dx[] = {0, 0, 1, -1, 0, 0};
  static int dy[] = {1, -1, 0, 0, 0, 0};
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int m = scin.nextInt();
    int n = scin.nextInt();
    int h = scin.nextInt();

    int matrix[][][] = new int[h][n][m];
    int dist[][][] = new int[h][n][m];

    Queue<Index> q = new LinkedList<Index>();

    for (int k=0; k<h; k++) {
      for (int i=0; i<n; i++) {
        for (int j=0; j<m; j++) {
            matrix[k][i][j] = scin.nextInt();
            dist[k][i][j] = -1;
            if (matrix[k][i][j] == 1) {
              q.add(new Index(k, i, j));
              dist[k][i][j] = 0;
            }
        }
      }
    }

    bfs3(q, matrix, dist);

    int ans = 0;
    for (int k=0; k<h; k++) {
      for (int i=0; i<n; i++) {
        for (int j=0; j<m; j++) {
          if (dist[k][i][j] > ans) {
            ans = dist[k][i][j];
          }
        }
      }
    }

    for (int k=0; k<h; k++) {
      for (int i=0; i<n; i++) {
        for (int j=0; j<m; j++) {
          if (matrix[k][i][j] == 0 && dist[k][i][j] == -1) {
            ans = -1;
          }
        }
      }
    }
    System.out.println(ans);
  }

  static void bfs3(Queue<Index>q, int [][][]m, int [][][]dist) {
    while(!q.isEmpty()) {
      Index item = q.remove();

      for (int i=0; i<6; i++) {
        int nh = item.h + dh[i];
        int nx = item.x + dx[i];
        int ny = item.y + dy[i];

        if (0 <= nh && nh < m.length &&
            0 <= nx && nx < m[0].length &&
            0 <= ny && ny < m[0][0].length) {

          if (dist[nh][nx][ny] == -1 && m[nh][nx][ny] != -1) {
            dist[nh][nx][ny] = dist[item.h][item.x][item.y] + 1;
            q.add(new Index(nh, nx, ny));
          }
        }
      }
    }
  }
}
