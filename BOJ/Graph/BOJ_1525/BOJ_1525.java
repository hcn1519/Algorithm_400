import java.util.*;

public class BOJ_1525 {
  static int[] dx = {0, 0, 1, -1};
  static int[] dy = {1, -1, 0, 0};
  static HashMap<Integer, Integer> d = new HashMap<Integer, Integer>();
  public static void main(String[] args) {
    Scanner scin = new Scanner(System.in);

    // 9자리로 표현되는 숫자
    int start = 0;
    for(int i=0; i<3; i++) {
      for(int j=0; j<3; j++) {
        int tmp = scin.nextInt();
        if (tmp == 0) {
          tmp = 9;
        }
        start = start * 10 + tmp;
      }
    }

    bfs(start);

    if(d.containsKey(123456789)) {
      System.out.println(d.get(123456789));
    } else {
      System.out.println("-1");
    }
  }

  static void bfs(int start) {
    Queue<Integer> q = new LinkedList<Integer>();
    q.add(start);
    d.put(start, 0);

    while(!q.isEmpty()){
      int nowNum = q.remove();
      String now = Integer.toString(nowNum);

      int z = now.indexOf('9');
      int xIndex = z / 3;
      int yIndex = z % 3;
      for(int i=0; i<4; i++) {
        int nx = dx[i] + xIndex;
        int ny = dy[i] + yIndex;

        if (0 <= nx && nx < 3 && 0 <= ny && ny < 3) {
          StringBuilder nextPuzzle = new StringBuilder(now);

          // 자리 스왑
          char tmp = nextPuzzle.charAt(xIndex * 3 + yIndex);
          nextPuzzle.setCharAt(xIndex * 3 + yIndex, nextPuzzle.charAt(nx*3+ny));
          nextPuzzle.setCharAt(nx * 3 + ny, tmp);

          int num = Integer.parseInt(nextPuzzle.toString());

          if (!d.containsKey(num)) {
            d.put(num, d.get(nowNum) + 1);
            q.add(num);
          }
        }
      }
    }

  }
}
