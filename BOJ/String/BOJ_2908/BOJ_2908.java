import java.util.*;

public class BOJ_2908 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    String input1 = scin.next();
    String input2 = scin.next();

    String x = String.valueOf(input1.charAt(2)) + String.valueOf(input1.charAt(1)) + String.valueOf(input1.charAt(0));
    String y = String.valueOf(input2.charAt(2)) + String.valueOf(input2.charAt(1)) + String.valueOf(input2.charAt(0));

    int a = Integer.parseInt(x);
    int b = Integer.parseInt(y);

    if (a > b) {
      System.out.println(a);
    } else {
      System.out.println(b);
    }

  }

}
