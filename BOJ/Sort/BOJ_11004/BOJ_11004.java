import java.util.*;

public class BOJ_11004 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    int k = scin.nextInt();

    int arr[] = new int[n];

    for(int i=0; i<n; i++) {
      arr[i] = scin.nextInt();
    }

    quickSort(arr, 0, n - 1);

    // for(int i=0; i<n; i++) {
    //   System.out.print(arr[i] + " ");
    // }
    // System.out.println();
    System.out.println(arr[k-1]);

  }

  static void quickSort(int arr[], int start, int end) {
    if (start < end) {
      int pivot = start;
      for(int i=start; i<end; i++) {
        if (arr[i] < arr[end]) {
          swap(arr, i, pivot);
          pivot++;
        }
      }
      swap(arr, pivot, end);
      quickSort(arr, start, pivot -1);
      quickSort(arr, pivot + 1, end);
    }
  }

  static void swap(int data[], int a, int b) {
    int temp = data[a];
    data[a] = data[b];
    data[b] = temp;
  }
}
