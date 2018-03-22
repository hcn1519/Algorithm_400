import java.util.*;

public class BOJ_1260 {
  static ArrayList<Integer>[] g;
  static boolean check1[];
  static boolean check2[];

  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int m = scin.nextInt();
    int start = scin.nextInt();

    g = (ArrayList<Integer>[]) new ArrayList[n+1];

    for (int i=1; i<=n; i++) {
      g[i] = new ArrayList<Integer>();
    }

    for (int i=0; i<m; i++) {
      int u = scin.nextInt();
      int v = scin.nextInt();

      if (!g[u].contains(v)) {
        g[u].add(v);
        g[v].add(u);
      }
    }

    for (int i=1; i<=n; i++) {
      Collections.sort(g[i]);
    }

    check1 = new boolean[n+1];
    check2 = new boolean[n+1];

    dfs(start);
    System.out.println();
    bfs(start);
    System.out.println();
  }

  static void dfs(int x) {
    check1[x] = true;
    System.out.print(x + " ");
    for (int i=0; i<g[x].size(); i++) {
      int y = g[x].get(i);
      if (check1[y] == false) {
        dfs(y);
      }
    }
  }
  static void bfs(int x) {
    Queue<Integer> q = new LinkedList<Integer>();
    q.add(x);
    check2[x] = true;

    while(!q.isEmpty()) {
      int e = q.remove();
      System.out.print(e + " ");
      for (int i=0; i<g[e].size(); i++) {
        int y = g[e].get(i);

        if(check2[y] == false) {
          check2[y] = true;
          q.add(y);
        }
      }
    }
  }
}
