import java.util.*;

public class BOJ_1188 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int m = scin.nextInt();

    System.out.println(sol2(n, m));

  }
  static int sol2(int n, int m) {
    int result = 0;
    while(n != m) {
      if (n < m) {
        result += n;
        m -= n;
      } else {
        n -= m;
      }

      if (n == 1) {
        result += (m-1);
        return result;
      } else if (m == 1){
        return result;
      }
    }
    return result;
  }
}
