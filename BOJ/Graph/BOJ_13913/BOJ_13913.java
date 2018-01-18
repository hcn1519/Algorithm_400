import java.util.*;

public class BOJ_13913 {
  static int max = 200001;
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int k = scin.nextInt();

    int dist[] = new int[max];
    int from[] = new int[max];
    boolean check[] = new boolean[max];

    bfs(dist, from, check, n, k);

    System.out.println(dist[k]);

    track(from, k);

    while(!st.empty()) {
      System.out.print(st.pop() + " ");
    }
    System.out.println();
  }
  static Stack<Integer> st = new Stack<Integer>();
  static void bfs(int[] dist, int[] from, boolean[] check, int n, int k) {
    Queue<Integer> q = new LinkedList<Integer>();
    q.add(n);
    check[n] = true;

    while(!q.isEmpty()) {
      int item = q.remove();

      if(item - 1 >= 0 && check[item - 1] == false) {
          q.add(item - 1);
          check[item - 1] = true;
          from[item - 1] = item;
          dist[item - 1] = dist[item] + 1;
      }

      if(item + 1 < max && check[item + 1] == false) {
        q.add(item + 1);
        check[item + 1] = true;
        from[item + 1] = item;
        dist[item + 1] = dist[item] + 1;
      }

      if(item * 2 < max && check[item * 2] == false) {
        q.add(item * 2);
        check[item * 2] = true;
        from[item * 2] = item;
        dist[item * 2] = dist[item] + 1;
      }
    }
  }
  static void track(int[] from, int k) {
    if(from[k] == 0) {
      st.push(k);
      return;
    }
    st.push(k);

    k = from[k];
    track(from, k);
  }
}
