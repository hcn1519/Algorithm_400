import java.util.*;

public class BOJ_13913 {
  static int max = 200000;
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int k = scin.nextInt();

    int dist[] = new int[max + 1];
    int from[] = new int[max + 1];
    boolean check[] = new boolean[max + 1];

    bfs(dist, from, check, n);

    System.out.println(dist[k]);

    track(from, n, k);

    while(!st.empty()) {
      System.out.print(st.pop() + " ");
    }
    System.out.println();
  }
  static Stack<Integer> st = new Stack<Integer>();

  static void bfs(int[] dist, int[] from, boolean[] check, int n) {
    Queue<Integer> q = new LinkedList<Integer>();
    q.add(n);
    check[n] = true;

    while(!q.isEmpty()) {
      int item = q.remove();

      if(item - 1 >= 0 && check[item - 1] == false) {
          q.add(item - 1);
          check[item - 1] = true;
          dist[item - 1] = dist[item] + 1;
          from[item - 1] = item;
      }

      if(item + 1 < max && check[item + 1] == false) {
        q.add(item + 1);
        check[item + 1] = true;
        dist[item + 1] = dist[item] + 1;
        from[item + 1] = item;
      }

      if(item * 2 < max && check[item * 2] == false) {
        q.add(item * 2);
        check[item * 2] = true;
        dist[item * 2] = dist[item] + 1;
        from[item * 2] = item;
      }
    }
  }

  static void track(int[] from, int n, int k) {
    while(n != k) {
      st.push(k);
      k = from[k];
    }
    st.push(k);
  }
}
