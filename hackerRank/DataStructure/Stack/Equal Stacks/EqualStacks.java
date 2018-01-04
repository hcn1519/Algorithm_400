import java.util.*;

public class EqualStacks {
  public static void main(String[] args ){
    Scanner scin = new Scanner(System.in);

    int n1 = scin.nextInt();
    int n2 = scin.nextInt();
    int n3 = scin.nextInt();

    int input1[] = setInput(n1, scin);
    int input2[] = setInput(n2, scin);
    int input3[] = setInput(n3, scin);

    Stack s[] = new Stack[3];
    s[0] = setStack(input1);
    s[1] = setStack(input2);
    s[2] = setStack(input3);

    int h[] = new int[3];
    h[0] = arraySum(input1);
    h[1] = arraySum(input2);
    h[2] = arraySum(input3);

    int minHeight = getMin(h);

    Boolean result[][] = new Boolean[minHeight+1][3];

    int finalResult = 0;
    for(int i = minHeight; i > 0; i--) {

      for(int k=0; k< 3; k++) {
        result[i][k] = canReachable(i, s[k], h[k]);
      }

      if (result[i][0] && result[i][1] && result[i][2]) {
        finalResult = i;
        break;
      }
    }

    System.out.println(finalResult);

  }
  static Boolean canReachable(int destination, Stack<Integer> st, int h) {
        if (h == destination) {
          return true;
        }

        Queue<Integer> temp = new LinkedList<>();

        while(!st.empty()) {
          int x = st.pop();
          temp.add(x);
          h -= x;

          if (h < destination) {
            while(!temp.isEmpty()) {
              st.push(temp.poll());
            }
            return false;
          } else if (h == destination) {
            while(!temp.isEmpty()) {
              st.push(temp.poll());
            }
            return true;
          }
        }
        while(!temp.isEmpty()) {
          st.push(temp.poll());
        }
        return false;
  }

  static int getMin(int input[]) {
    int min = input[0];
    for(int i=1; i<input.length; i++) {
      if (min > input[i]) {
        min = input[i];
      }
    }
    return min;
  }
  static int arraySum(int input[]) {
    int sum = 0;
    for(int i=0; i<input.length; i++) {
      sum += input[i];
    }
    return sum;
  }
  static int[] setInput(int n, Scanner scin) {
    int input[] = new int[n];
    for(int i=0; i<n; i++) {
      int x = scin.nextInt();
      input[i] = x;
    }
    return input;
  }
  static Stack<Integer> setStack(int input[]) {
    Stack<Integer> s = new Stack<>();
    for(int i=input.length-1; i>=0; i--) {
      s.push(input[i]);
    }
    return s;
  }
}
