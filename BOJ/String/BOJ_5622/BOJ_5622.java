import java.util.*;

public class BOJ_5622 {

  static HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    char alphabets[] = {'A', 'B', 'C', 'D', 'E',
                      'F', 'G', 'H', 'I', 'J',
                      'K', 'L', 'M', 'N', 'O',
                      'P', 'Q', 'R', 'S', 'T',
                      'U', 'V', 'W', 'X', 'Y', 'Z'};

    int time = 2;
    for (int i=0; i<alphabets.length; i++) {
      if (i % 3 == 0) {
        time++;
      }
      dict.put(alphabets[i], time);
    }

    dict.put(alphabets[18], 8);
    dict.put(alphabets[21], 9);
    dict.put(alphabets[24],10);
    dict.put(alphabets[25], 10);


    String input = scin.next();

    int ans = 0;
    for (int i=0; i<input.length(); i++) {
        char temp = input.charAt(i);
        ans += dict.get(temp);
    }
    System.out.println(ans);
  }
}
