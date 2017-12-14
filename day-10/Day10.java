public class Day10 {

  public static void main(String[] args) {
    int[] input = {106,16,254,226,55,2,1,166,177,247,93,0,255,228,60,36};
    int[] hash = knotHash(input);
    System.out.println(hash[0] * hash[1]); 
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

}
