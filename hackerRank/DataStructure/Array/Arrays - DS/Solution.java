import java.util.*;

public class Solution {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    Stack<Integer> st = new Stack<Integer>();
    int n = scin.nextInt();
    int arr[] = new int[n];

    for(int i=0; i<n; i++) {
      arr[i] = scin.nextInt();
      st.push(arr[i]);
    }

    while(!st.empty()) {
      System.out.print(st.pop() + " ");
    }
    System.out.println();
  }
}
