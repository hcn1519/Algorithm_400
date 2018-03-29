import java.util.*;

public class BOJ_2750 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();

    int input[] = new int[n];

    for (int i=0; i<n; i++) {
        input[i] = scin.nextInt();
    }

    Arrays.sort(input);
    for (int i=0; i<n; i++) {
        System.out.println(input[i]);
    }
  }
}
