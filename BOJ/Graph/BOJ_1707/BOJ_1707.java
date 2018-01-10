import java.util.*;

public class BOJ_1707 {
  static ArrayList<Integer>[] g;
  static int check[];
  static boolean c[];

  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int k = scin.nextInt();

    for(int i=0; i < k; i++) {
      int n = scin.nextInt();
      int m = scin.nextInt();
      getResult(n, m, scin);
    }

  }
  static void getResult(int n, int m, Scanner scin) {
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

    check = new int[n+1];
    c = new boolean[n+1];

    for(int i=1; i<=n; i++) {
      if (check[i] == 0){
        bfs(i);
      }
    }


    boolean result = true;
    for(int i=1; i<=n; i++) {
      for(int k=0; k<g[i].size(); k++) {
        if (check[i] == check[g[i].get(k)]) {
            result = false;
            break;
        }
      }
      if (result == false) {
        break;
      }
    }

    if (result) {
      System.out.println("YES");
    } else {
      System.out.println("NO");
    }
  }
  // queue에 넣었을 때 방문했음을 체크
  static void bfs(int x) {
    Queue<Integer> q = new LinkedList<Integer>();
    q.add(x);
    check[x] = 1;
    c[x] = true;

    while(!q.isEmpty()) {
      int e = q.remove();

      for(int i=0; i<g[e].size(); i++) {
        int y = g[e].get(i);

        if (check[e] == 1 && c[y] == false) {
          check[y] = 2;
          c[y] = true;
          q.add(y);
        } else if (check[e] == 2 && c[y] == false) {
          check[y] = 1;
          c[y] = true;
          q.add(y);
        }
      }
    }

  }
}
