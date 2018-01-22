import java.util.*;

public class BOJ_14226 {
  static int max = 1000;
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int time[][] = new int[max + 1][max + 1];
    boolean check[][] = new boolean[max + 1][max + 1];

    bfs(time, check, 1, 0);

    int min = -1;
    for(int i=0; i < max; i++) {
      if (check[n][i] == true) {
        if (min == -1 || min > time[n][i]) {
          min = time[n][i];
        }
      }
    }

    System.out.println(min);
  }

  static void bfs(int time[][], boolean check[][], int start, int clipboard) {
    Queue<Integer> q = new LinkedList<Integer>();
    q.add(start);
    q.add(clipboard);
    check[start][clipboard] = true;

    while(!q.isEmpty()) {
      int s = q.remove();
      int c = q.remove();

      if(check[s][s] == false) {
          time[s][s] = time[s][c] + 1;
          check[s][s] = true;
          q.add(s);
          q.add(s);
      }

      if (s-1 >= 0 && check[s-1][c] == false) {
        time[s-1][c] = time[s][c] + 1;
        check[s-1][c] = true;
        q.add(s-1);
        q.add(c);
      }

      if(s+c <= max && check[s+c][c] == false) {
        time[s+c][c] = time[s][c] + 1;
        check[s+c][c] = true;
        q.add(s+c);
        q.add(c);
      }
    }
  }
}
