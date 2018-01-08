import java.util.*;

public class BOJ_6588 {
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    boolean eratos[] = new boolean[1000001];

    for(int i=2; i<1000001; i++) {
        if (eratos[i] == false ) {
          for(int j=i*i; j<1000001 && i < 1000; j+=i) {
              eratos[j] = true;
          }
        }
    }

    // false면 소수
    // true면
    int n = -1;
    while(n != 0) {
        n = scin.nextInt();
        if (n != 0) {
          if (checkPrime(eratos, n) == true) {
            // 8 = 3 + 5
            System.out.print(n + " = " + prime1 + " + " + prime2);
            System.out.println();
          } else {
            System.out.println("Goldbach's conjecture is wrong.");
          }
        }
    }

  }
  static int prime1 = 0;
  static int prime2 = 0;
  static boolean checkPrime(boolean[] eratos, int n) {
    prime1 = 0;
    prime2 = 0;
    for(int i=2; i*i<eratos.length; i++) {
      if (eratos[i] == false && eratos[n-i] == false) {
        prime1 = i;
        prime2 = n-i;
        return true;
      }
    }
    return false;
  }
}
