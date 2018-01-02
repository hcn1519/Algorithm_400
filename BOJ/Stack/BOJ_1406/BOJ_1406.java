import java.util.*;

public class BOJ_1406 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);
    String prevString = scin.next();
    int n = scin.nextInt();

    Stack leftStack = new Stack<String>();
    Stack rightStack = new Stack<String>();
    for(int i = 0; i < prevString.length(); i++) {
      leftStack.push(prevString.charAt(i));
    }

    for(int i = 0; i < n; i++) {
      String input = scin.next();

      if (input.equals("L")) {
        if (!leftStack.empty()) {
          rightStack.push(leftStack.pop());
        }
      } else if (input.equals("D")) {
        if (!rightStack.empty()) {
          leftStack.push(rightStack.pop());
        }
      } else if (input.equals("B")) {
        if (!leftStack.empty()) {
          leftStack.pop();
        }
      } else {
          // P
        String temp = scin.next();
        leftStack.push(temp.charAt(0));
      }
    }

    ArrayList left = new ArrayList();

    while (!leftStack.empty()) {
      left.add(leftStack.pop());
    }

    for(int i=left.size()-1; i>=0; i--) {
      System.out.print(left.get(i));
    }
    while (!rightStack.empty()) {
      System.out.print(rightStack.pop());
    }
    System.out.println();
  }
}
