import java.util.*;

public class BOJ_9019 {
  static int max = 200000;
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int k = scin.nextInt();

    for(int i=0; i<k; i++) {
      int start = scin.nextInt();
      int destination = scin.nextInt();

      char result[] = new char[max+1];
      boolean check[] = new boolean[max+1];
      int from[] = new int[max+1];

      bfs(start, result, check, from);


      Stack<Character> st = new Stack<Character>();

      track(st, destination, from, result);

      while(!st.empty()) {
        System.out.print(st.pop());
      }
      System.out.println();
    }

    // for(int i=0; i<20; i++) {
    //   System.out.print(result[i] + " ");
    // }
    // System.out.println();

  }
  static void track(Stack<Character> st, int destination, int from[], char result[]) {
    while(from[destination] != -1) {
      st.push(result[destination]);
      destination = from[destination];
    }
  }

  static void bfs(int start, char result[], boolean check[], int from[]) {
    Queue<Integer> q = new LinkedList<Integer>();
    q.add(start);
    check[start] = true;
    from[start] = -1;

    while(!q.isEmpty()) {
      int n = q.remove();

      if (n * 2 < 10000) {
        if (check[n*2] == false) {
          from[n*2] = n;
          result[n*2] = 'D';
          check[n*2] = true;
          q.add(n*2);
        }
      } else {
        int newIdx = (n*2) % 10000;
        if (check[newIdx] == false) {
          from[newIdx] = n;
          result[newIdx] = 'D';
          check[newIdx] = true;
          q.add(newIdx);
        }
      }

      if (n - 1 >= 0) {
        if (check[n - 1] == false) {
          from[n-1] = n;
          result[n - 1] = 'S';
          check[n - 1] = true;
          q.add(n - 1);
        }
      } else if (n - 1 == -1) {
        int newIdx = 9999;
        if (check[newIdx] == false) {
          from[newIdx] = n;
          result[newIdx] = 'S';
          check[newIdx] = true;
          q.add(newIdx);
        }
      }

      int idxL = (n/1000) + (n%1000) * 10;
      int idxR = (n%10) * 1000 + (n/10);

      if (check[idxL] == false) {
        from[idxL] = n;
        result[idxL] = 'L';
        check[idxL] = true;
        q.add(idxL);
      }

      if (check[idxR] == false) {
        from[idxR] = n;
        result[idxR] = 'R';
        check[idxR] = true;
        q.add(idxR);
      }
    }
  }
}
