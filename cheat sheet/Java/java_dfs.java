import java.util.*;

public class java_dfs {
  static ArrayList<Integer>[] g;
  static boolean[] check;
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int m = scin.nextInt();

    g = (ArrayList<Integer>[]) new ArrayList[n+1];

    // 2차원 ArrayList initialize
    for(int i=1; i<=n; i++){
      g[i] = new ArrayList<Integer>();
    }

    // 인접 리스트 방식
    for(int i=0; i<m; i++){
      int u = scin.nextInt();
      int v = scin.nextInt();

      // 무방향 그래프
      g[u].add(v);
      g[v].add(u);
    }

    check = new boolean[n+1];

    dfs(1);
  }
  // dfs
  static void dfs(int x){
    check[x] = true;
    System.out.print(x + " ");
    for(int i=0; i<g[x].size(); i++) {
      int y = g[x].get(i);
      if (check[y] == false) {
        dfs(y);
      }
    }
  }
}
