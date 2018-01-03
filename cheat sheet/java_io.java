import java.io.*;

public class java_io {
  public static void main(String[] args) throws java.io.IOException {

    System.out.println("Enter your name");

    String name = readConsoleLine();
    System.out.println("Welcome "+name);
  }
  // BufferedReader 이용한 예제
  static String readConsoleLine() throws java.io.IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    return br.readLine();
  }

  // Scanner 이용한 예제
  static void readData() {
    Scanner scin = new Scanner(System.in);

    int n = scin.nextInt();
    double x = scin.nextDouble();
    String y = scin.next();
  }
}
