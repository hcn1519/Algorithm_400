import java.util.*;
import java.math.BigInteger;

public class BOJ_2163 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int m = scin.nextInt();

    System.out.println(cutChoco(n, m));
  }

  static int cutChoco(int n, int m) {
      return (n*m - 1);
  }
}
