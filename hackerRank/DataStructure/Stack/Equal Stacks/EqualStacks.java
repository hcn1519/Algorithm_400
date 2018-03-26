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

    ArrayList<Integer> index1 = new ArrayList<>();
    ArrayList<Integer> index2 = new ArrayList<>();

    for(int i = minHeight; i > 0; i--) {
        result[i][0] = canReachable(i, s[0], h[0]);
        if (result[i][0]) {
            index1.add(i);
        }
    }

    for(int i = 0; i<index1.size(); i++) {
        if (result[index1.get(i)][0]) {
            result[i][1] = canReachable(i, s[1], h[1]);
        }
        if (result[i][1]) {
            index2.add(index1.get(i));
        }
    }

    int finalResult = 0;
    for(int i = 0; i<index2.size(); i++) {
        if (result[index2.get(i)][0]) {
            result[i][2] = canReachable(i, s[2], h[2]);
        }
        if (result[i][2]) {
            finalResult = index2.get(i);
            break;
        }
    }

    System.out.println(finalResult);

  }
  static Boolean canReachable(int destination, Stack<Integer> st, int h) {
        if (h == destination) {
          return true;
        }

        Stack<Integer> temp = (Stack<Integer>)st.clone();

        while(!temp.empty()) {
          int x = temp.pop();
          h -= x;
          if (h < destination) {
            return false;
          } else if (h == destination) {
            return true;
          }
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
