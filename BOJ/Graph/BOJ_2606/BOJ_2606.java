import java.util.*;

public class BOJ_2606 {
  static ArrayList<Integer>[] g;
  static boolean check[];

  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int m = scin.nextInt();

    g = (ArrayList<Integer>[]) new ArrayList[n+1];

    for (int i=1; i<=n; i++) {
      g[i] = new ArrayList<Integer>();
    }


    for (int i = 0; i<m; i++) {
      int u = scin.nextInt();
      int v = scin.nextInt();

      g[u].add(v);
      g[v].add(u);
    }

    check = new boolean[n+1];

    dfs(1);

    int ans = 0;
    for (int i=2; i<=n; i++) {
      if (check[i] == true) {
        ans++;
      }
    }
    System.out.println(ans);

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
