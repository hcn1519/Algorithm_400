import java.util.*;

class Index {
  int x;
  int y;
  Index(int i, int j) {
    this.x = i;
    this.y = j;
  }
}
public class BOJ_2178 {

  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

      int w = scin.nextInt();
      int h = scin.nextInt();

      scin.nextLine();

      int m[][] = new int[w][h];
      boolean check[][] = new boolean[w][h];

      int result[][] = new int[w][h];

      for(int i=0; i<w; i++) {
          String line = scin.nextLine();
          for(int j=0; j<line.length(); j++) {
              char c = line.charAt(j);
              m[i][j] = Character.getNumericValue(c);
          }
      }

      bfs(m, result, check, 0, 0);

      System.out.println(result[w-1][h-1]);

    }
    static int dx[] = {0, 0, 1, -1};
    static int dy[] = {1, -1, 0, 0};

    static void bfs(int[][] m, int[][] result, boolean[][] check, int x, int y) {
      Queue<Index> q = new LinkedList<Index>();
      Index idx = new Index(x, y);
      q.add(idx);
      check[0][0] = true;
      result[0][0] = 1;

      while(!q.isEmpty()) {
          Index item = q.remove();

          for(int i=0; i<4; i++) {
            int nx = item.x + dx[i];
            int ny = item.y + dy[i];
            if (0 <= nx && nx < m.length && 0 <= ny && ny < m[0].length) {
              if(m[nx][ny] == 1 && check[nx][ny] == false) {
                  Index temp = new Index(nx, ny);
                  check[nx][ny] = true;
                  result[nx][ny] = result[item.x][item.y] + 1;
                  q.add(temp);
              }
            }
          }
      }
    }
  }
