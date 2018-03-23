import java.util.*;

public class BOJ_10451 {
  static ArrayList<Integer>[] g;
  static boolean[] check;

  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int testCase = scin.nextInt();

    for (int t=1; t<=testCase; t++) {
      int n = scin.nextInt();

      g = (ArrayList<Integer>[]) new ArrayList[n+1];

      for (int i=0; i<=n; i++) {
        g[i] = new ArrayList<Integer>();
      }

      for(int i=1; i<=n; i++) {
        int v = scin.nextInt();

        g[i].add(v);
      }
      check = new boolean[n+1];

      int count = 0;
      for(int i=1; i<=n; i++) {
        if (check[i] == false) {
          dfs(i);
          count++;
        }
      }
      System.out.println(count);
    }
  }

  static void dfs(int x) {
    check[x] = true;
    for (int i=0; i<g[x].size(); i++) {
      int y = g[x].get(i);
      if (check[y] == false) {
        dfs(y);
      }
    }
  }
}
