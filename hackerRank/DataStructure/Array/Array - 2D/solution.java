import java.util.*;

public class solution {
  public static void main(String[] args) {
      Scanner scin = new Scanner(System.in);

      int matrix[][] = new int[6][6];

      for(int i=0; i<6; i++) {
        for(int k=0; k<6; k++) {
            matrix[i][k] = scin.nextInt();
        }
      }

      int max = sumHourGlass(0, 0, matrix);

      for(int i=0; i<=3; i++) {
        for(int k=0; k<=3; k++) {
            int candidate = sumHourGlass(i, k, matrix);
            if (max < candidate) {
              max = candidate;
            }
        }
      }
      System.out.println(max);
  }

  static int sumHourGlass(int row, int col, int matrix[][]) {
    int sum = matrix[row][col] + matrix[row][col + 1] + matrix[row][col + 2];
    sum += (matrix[row + 1][col + 1] + matrix[row + 2][col] + matrix[row + 2][col + 1]);
    sum += matrix[row + 2][col + 2];
    return sum;
  }
}
