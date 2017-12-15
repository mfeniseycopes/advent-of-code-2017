import java.util.*;

public class Day10 {

  public static void main(String[] args) {
    int[] input = {106,16,254,226,55,2,1,166,177,247,93,0,255,228,60,36};

    // part 1
    int[] hash = knotHash(input);
    System.out.println(hash[0] * hash[1]); 

    // part 2
    System.out.println(knotHash2("106,16,254,226,55,2,1,166,177,247,93,0,255,228,60,36"));
  }

  public static int[] knotHash(int[] lengths) {

    // setup list
    int[] list = new int[256];
    for (int i = 0; i < list.length; i++) {
      list[i] = i;
    }

    int pos = 0;
    int skip = 0;

    for (int i = 0; i < lengths.length; i++) {
      int length = lengths[i]; 

      // reverse length starting from pos
      for (int j = 0; j < (length / 2); j++) {
        int left = (pos + j) % 256;
        int right = (pos + length - j - 1) % 256;
        int temp = list[left];
        list[left] = list[right];
        list[right] = temp;
      }

      // update pos and skip
      pos = (pos + length + skip) % 256;
      skip++;
    }

    return list;
  }
  
  public static String knotHash2(String str) {

    // setup list
    int[] list = new int[256];
    for (int i = 0; i < list.length; i++) {
      list[i] = i;
    }

    // convert string to int array
    List<Integer> lengths = new ArrayList<Integer>();
    for (int i = 0; i < str.length(); i++) {
      lengths.add((int)str.charAt(i));
    }
    int[] offset = {17, 31, 73, 47, 23} ;
    for (int i = 0; i < offset.length; i++) {
      lengths.add(offset[i]);
    }
    

    int pos = 0;
    int skip = 0;
    // 64 rounds 
    for (int repeats = 0; repeats < 64; repeats++) {
      // knot hash
      for (int i = 0; i < lengths.size(); i++) {
        int length = lengths.get(i); 

        // reverse length starting from pos
        for (int j = 0; j < (length / 2); j++) {
          int left = (pos + j) % 256;
          int right = (pos + length - j - 1) % 256;
          int temp = list[left];
          list[left] = list[right];
          list[right] = temp;
        }

        // update pos and skip
        pos = (pos + length + skip) % 256;
        skip++;
      }
    }

    // make dense hash
    StringBuilder denseHashBuilder = new StringBuilder("");
    for (int i = 0; i < 16; i++) {
      int xor = list[i * 16];
      for (int j = 1; j < 16; j++) {
        xor ^= list[i * 16 + j]; 
      }
      String hexStr = Integer.toHexString(xor);
      if (hexStr.length() == 1) 
        denseHashBuilder.append("0");
      denseHashBuilder.append(Integer.toHexString(xor));
    }

    return denseHashBuilder.toString();
  }

}
