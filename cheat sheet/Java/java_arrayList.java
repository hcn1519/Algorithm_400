import java.util.*;

public class java_arrayList {
  static ArrayList<Integer>[] g;

  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();

    g = (ArrayList<Integer>[]) new ArrayList[n];

    for(int i=0; i<n; i++) {
      g[i] = new ArrayList<Integer>();
    }
  }
}
