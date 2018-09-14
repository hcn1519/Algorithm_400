import java.util.*;

public class java_bfs {
  static ArrayList<Integer>[] g;
  static boolean check[];

  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    // int case = scin.nextInt();

    int n = scin.nextInt();
    int m = scin.nextInt();

    g = (ArrayList<Integer>[]) new ArrayList[n+1];

    for(int i=1; i<=n; i++) {
        g[i] = new ArrayList<Integer>();
    }

    for (int i=0; i<m; i++) {
        int u = scin.nextInt();
        int v = scin.nextInt();

        g[u].add(v);
        g[v].add(u);
    }

    check = new boolean[n+1];

    bfs(1);
  }

  // queue에 넣었을 때 방문했음을 체크
  static void bfs(int x) {
    Queue<Integer> q = new LinkedList<Integer>();
    q.add(x);
    check[x] = true;

    while(!q.isEmpty()) {
      int e = q.remove();
      System.out.print(e + " ");

      for(int i=0; i<g[e].size(); i++) {
        int y = g[e].get(i);

        if (check[y] == false) {
          check[y] = true;
          q.add(y);
        }
      }
    }

  }
}
