import java.util.*;

class Index {
  int x;
  int y;
  Index(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class BOJ_2146 {
  static int dx[] = {0, 0, 1, -1};
  static int dy[] = {1, -1, 0, 0};

  public static void main(String[] args) {

    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int m[][] = new int[n][n];
    int r[][] = new int[n][n];

    for(int i=0; i<n; i++) {
      for(int j=0; j<n; j++) {
        m[i][j] = scin.nextInt();
      }
    }

    for(int i=0; i<n; i++) {
      for(int j=0; j<n; j++) {
        r[i][j] = m[i][j];
      }
    }

    // 1. 그룹 나누기
    int c = 1;
    boolean[][] check = new boolean[n][n];

    for(int i=0; i<n; i++) {
      for(int j=0; j<n; j++) {
        if(m[i][j] == 1 && check[i][j] == false) {
          m[i][j] = c;
          dfs(m, check, i, j, c);
          c++;
        }
      }
    }

    int ans = -1;
    int[][] dist = new int[n][n];
    for(int k=1; k<c; k++) {
        // bfs
        // Queue에 그룹별로 넣기
        Queue<Index> q = new LinkedList<Index>();
        for(int i=0; i<n; i++) {
          for(int j=0; j<n; j++) {
            // 바다
            dist[i][j] = -1;

            // 육지
            if(m[i][j] == k) {
              q.add(new Index(i, j));
              dist[i][j] = 0;
            }
          }
        }

        while(!q.isEmpty()) {
          Index item = q.remove();

          for(int i=0; i<4; i++) {
            int nx = item.x + dx[i];
            int ny = item.y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < n) {
              if(dist[nx][ny] == -1) {
                dist[nx][ny] = dist[item.x][item.y] + 1;
                q.add(new Index(nx, ny));
              }
            }
          }
        }

        // System.out.println();
        // for(int i=0; i<n; i++) {
        //   for(int j=0; j<n; j++) {
        //     System.out.print(dist[i][j] + " ");
        //   }
        //   System.out.println();
        // }

        for(int i=0; i<n; i++) {
          for(int j=0; j<n; j++) {
            // 다른 그룹
            if(r[i][j] == 1 && dist[i][j] != 0) {
              // 값 업데이트
              if(ans == -1 || dist[i][j]-1 < ans) {
                ans = dist[i][j]-1;
              }
            }
          }
        }
    }
    System.out.println(ans);
  }

  static void dfs(int[][] m, boolean[][] check, int x, int y, int val) {
    check[x][y] = true;
    for(int i=0; i<4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (0 <= nx && nx < m.length && 0 <= ny && ny < m[0].length) {
        if (check[nx][ny] == false && m[nx][ny] == 1) {
            m[nx][ny] = val;
            dfs(m, check, nx, ny, val);
        }
      }
    }
  }

}
