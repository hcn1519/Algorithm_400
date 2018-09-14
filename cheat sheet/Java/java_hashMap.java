import java.util.*;

public class java_hashMap {

  static HashMap<Integer, Integer> dict = new HashMap<Integer, Integer>();
  public static void main(String[] args) {

    dict.put(3, 124);

    if dict.containsKey(3) {
        System.out.println(dict.get(3));
    }
    
  }
}
