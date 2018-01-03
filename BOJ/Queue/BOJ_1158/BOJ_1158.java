import java.util.*;
import java.io.*;

public class BOJ_1158 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int m = scin.nextInt();

    Queue<Integer> q = josephus(n, m);

    printQ(q);
  }
  static Queue<Integer> josephus(int n, int m) {
    Queue<Integer> q = new LinkedList<>();

    for (int i=0; i<n; i++)
        q.add(i+1);

    int cnt = n;

    Queue<Integer> result = new LinkedList<>();
    while(cnt > 0) {
      for (int i=1; i<=m; i++) {
        if (i != m) {
            q.add(q.poll());
        } else {
            result.add(q.poll());
        }
      }

      cnt--;
    }
    return result;
  }

  static void printQ(Queue<Integer> q) {
    System.out.print("<");

    while (!q.isEmpty()) {
      if (q.size() != 1) {
        System.out.print(q.poll() + ", ");
      } else {
        System.out.print(q.poll());
      }
    }

    System.out.print(">");
    System.out.println("");
  }
}
