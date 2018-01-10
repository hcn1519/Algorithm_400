import java.util.*;

public class BOJ_13023_2 {
  static ArrayList<Integer>[] g;
  static boolean ans;
  static boolean ch[];

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

    for(int i=0; i<n; i++) {
      ch = new boolean[n];
      dfs(i, 1);
      if (ans) {
        break;
      }
    }
    if (ans) {
        System.out.println(1);
    } else {
        System.out.println(0);
    }

  }

  static void dfs(int x, int cnt) {
    if (cnt == 5) {
      ans = true;
      return;
    }

    ch[x] = true;
    for(int i=0; i<g[x].size(); i++) {
        int y = g[x].get(i);
        if(ch[y] == false) {
          dfs(y, cnt+1);
        }
    }
    ch[x] = false;
  }
}
