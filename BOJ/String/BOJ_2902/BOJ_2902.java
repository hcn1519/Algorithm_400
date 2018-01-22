import java.util.*;

public class BOJ_2902 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    String input = scin.next();

    String strParts[] = input.split("-");

    String result = "";
    for(int i=0; i<strParts.length; i++) {
      String k = Character.toString(strParts[i].charAt(0));
      result += k;
    }
    System.out.println(result);
  }
  
}
