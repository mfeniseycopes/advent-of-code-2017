import java.lang.Math;

public class Day3 {
  public static void main(String[] args) {
    int width = (int)Math.sqrt(368078) + 1;

    // part 1
    int[] pos = findPosition(368078, width);
    int[] pos1 = {width / 2, width / 2};
    System.out.println(Math.abs(pos1[0] - pos[0]) + Math.abs(pos1[1] - pos[1]));
    
    // part 2
    int additiveValue = getAdditiveValue(width, 368078);
    System.out.println(additiveValue);
  }

  public static int[] findPosition(int location, int width) {
    int last = width - 2;
    int next = width;

    int[] pos = {width - 2, width - 1}; // [row, col]
    int remaining = location - (int)Math.pow(last, 2) - 1;
    
    // up
    if (remaining >= last) {
      remaining -= last;
      pos[0] = 0;
    } else if (remaining > 0) {
      pos[0] -= remaining;
      remaining = 0;
    }
    
    // left
    if (remaining >= last + 1) {
      remaining -= last + 1;
      pos[1] = 0;
    } else if (remaining > 0) {
      pos[1] -= remaining;
      remaining = 0;
    }
    
    // down
    if (remaining >= last + 1) {
      remaining -= last + 1;
      pos[0] = last + 1;
    } else if (remaining > 0) {
      pos[0] += remaining;
      remaining = 0;
    }
    
    // right
    if (remaining >= last + 1) {
      remaining -= last + 1;
      pos[1] = last + 1;
    } else if (remaining > 0) {
      pos[1] += remaining;
      remaining = 0;
    }
    return pos;
  }

  public static int getAdditiveValue(int width, int location) {
    int[][] spiral = new int[width][width];

    int r = width / 2;
    int c = width / 2;
    int sqr = 1;
    int val = 1;
    int additiveVal = 0;

    spiral[r][c] = 1;
    val++;
    c++;
    sqr += 2;

    while (sqr <= width) {

      // up
      for (int j = 0; j < sqr - 2; j++) {
        additiveVal = setAdditiveValue(r, c, spiral);
        
        if (additiveVal > location) return additiveVal;

        r--;
      }

      // left
      for (int j = 0; j < sqr - 1; j++) {
        additiveVal = setAdditiveValue(r, c, spiral);
        
        if (additiveVal > location) return additiveVal;
        
        c--;
      }

      // down
      for (int j = 0; j < sqr - 1; j++) {
        additiveVal = setAdditiveValue(r, c, spiral);
        
        if (additiveVal > location) return additiveVal;
        
        r++;
      }

      // right
      for (int j = 0; j < sqr; j++) {
        additiveVal = setAdditiveValue(r, c, spiral);
        
        if (additiveVal > location) return additiveVal;
        
        c++;
      }

      sqr += 2;
    }

    return 0;
  }

  public static int setAdditiveValue(int r, int c, int[][] spiral) {
    int width = spiral.length;
    int additiveVal = 0;

    if (r - 1 >= 0 && c - 1 >= 0) additiveVal += spiral[r - 1][c - 1];
    if (r - 1 >= 0) additiveVal += spiral[r - 1][c];
    if (r - 1 >= 0 && c + 1 < width) additiveVal += spiral[r - 1][c + 1];
    if (c - 1 >= 0) additiveVal += spiral[r][c - 1];
    if (c + 1 < width) additiveVal += spiral[r][c + 1];
    if (r + 1 < width && c - 1 >= 0) additiveVal += spiral[r + 1][c - 1];
    if (r + 1 < width) additiveVal += spiral[r + 1][c];
    if (r + 1 < width && c + 1 < width) additiveVal += spiral[r + 1][c + 1];
    spiral[r][c] = additiveVal;
    
    return additiveVal; 
  }

}
