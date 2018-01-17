import java.util.*;

class Index {
  int x;
  int y;
  Index(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class BOJ_7576 {
  static int dx[] = {0, 0, 1, -1};
  static int dy[] = {1, -1, 0, 0};

  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int w = scin.nextInt();
    int h = scin.nextInt();

    int m[][] = new int[h][w];
    boolean check[][] = new boolean[h][w];

    int dist[][] = new int[h][w];

    Queue<Index> q = new LinkedList<Index>();
    for(int i=0; i<h; i++) {
      for(int j=0; j<w; j++) {
        m[i][j] = scin.nextInt();
        dist[i][j] = -1;
        if (m[i][j] == 1) {
          q.add(new Index(i, j));
          dist[i][j] = 0;
        }
      }
    }

    bfs(q, m, dist);

    int ans = 0;
    for(int i=0; i<h; i++) {
      for(int j=0; j<w; j++) {
        if(ans < dist[i][j]) {
          ans = dist[i][j];
        }
      }
    }

    for(int i=0; i<h; i++) {
      for(int j=0; j<w; j++) {
        if(m[i][j] == 0 && dist[i][j] == -1) {
          ans = -1;
        }
      }
    }
    System.out.println(ans);
  }
  static void bfs(Queue<Index> q, int[][] m, int[][]dist) {

    while(!q.isEmpty()) {
      Index item = q.remove();

      for(int i=0; i<4; i++) {
        int nx = item.x + dx[i];
        int ny = item.y + dy[i];
        if (0 <= nx && nx < m.length && 0 <= ny && ny < m[0].length) {
          if (dist[nx][ny] == -1 && m[nx][ny] == 0) {
            dist[nx][ny] = dist[item.x][item.y] + 1;
            q.add(new Index(nx, ny));
          }

        }
      }

    }

  }
}
