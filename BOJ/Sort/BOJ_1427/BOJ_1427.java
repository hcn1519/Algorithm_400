import java.util.*;

public class BOJ_1427 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    String n = scin.next();

    int arr[] = new int[n.length()];

    for(int i=0; i<n.length(); i++) {
      char temp = n.charAt(i);
      arr[i] = Character.getNumericValue(temp);
    }

    quickSort(arr, 0, n.length() - 1);

    for(int i=0; i<n.length(); i++) {
      System.out.print(arr[i]);
    }
    System.out.println();
  }

  static void quickSort(int arr[], int start, int end) {
    if (start < end) {
      int pivot = start;
      for (int i=start; i<end; i++) {
        if (arr[i] > arr[end]) {
          swap(arr, i, pivot);
          pivot++;
        }
      }

      swap(arr, pivot, end);
      quickSort(arr, start, pivot - 1);
      quickSort(arr, pivot + 1, end);
    }
  }

  static void swap(int arr[], int a, int b) {
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }
}
