import java.util.*;

public class BOJ_13023 {
  static ArrayList<Integer>[] g;
  static boolean check[][];
  static int d[][];

  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int m = scin.nextInt();

    g = (ArrayList<Integer>[]) new ArrayList[n];

    for(int i=0; i<n; i++){
      g[i] = new ArrayList<Integer>();
    }


    for(int i=0; i<m; i++) {
      int from = scin.nextInt();
      int to = scin.nextInt();

      g[from].add(to);
      g[to].add(from);
    }


    check = new boolean[n][n];
    d = new int[n][n];

    for(int i=0; i<n; i++) {
      depth = 0;
      dfs(i, i, 0);
    }

    int result = 0;
    for(int i=0; i<n; i++) {
      for(int j=0; j<n; j++) {
        if (d[i][j] >= 4) {
            result = 1;
            break;
        }
      }
      if (result == 1) {
        break;
      }
    }
    System.out.println(result);
  }

// 왜 이 소스는 작동하지 않을까..?
  static int depth = 0;
  static void dfs(int k, int x, int depth) {
    check[k][x] = true;
    d[k][x] = depth;
    for(int i=0; i<g[x].size(); i++) {
      int y = g[x].get(i);
      if (check[k][y] == false) {
        dfs(k, y, depth + 1);
      }
    }
  }
}
