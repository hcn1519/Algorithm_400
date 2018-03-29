import java.util.*;

public class BOJ_1325 {
  static ArrayList<Integer>[] g;
  static boolean[] check;

  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int m = scin.nextInt();

    g = (ArrayList<Integer>[]) new ArrayList[n+1];

    for (int i=1; i<=n; i++) {
      g[i] = new ArrayList<Integer>();
    }

    for (int i=0; i<m; i++) {
      int v = scin.nextInt();
      int u = scin.nextInt();

      g[u].add(v);
    }
    int dist[] = new int[n+1];

    for (int i=1; i<=n; i++) {
        check = new boolean[n+1];

        dfs(i);
        int counter = 0;
        for (int k=1; k<=n; k++) {
          if (check[k] == true) {
            counter++;
          }
        }
        dist[i] = counter;
    }

    int max = dist[0];
    for (int i=1; i<=n; i++) {
      if (dist[i] > max) {
        max = dist[i];
      }
    }

    ArrayList<Integer> ans = new ArrayList<Integer>();

    for (int i=1; i<=n; i++) {
        if (dist[i] == max) {
          ans.add(i);
        }
    }

    for(int i=0; i<ans.size(); i++) {
      System.out.print(ans.get(i) + " ");
    }
    System.out.println();
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
