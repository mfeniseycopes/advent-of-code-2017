import java.io.*;
import java.util.*;

public class Day5 {

  public static void main(String[] args) {
    List<Integer> jumps = readJumpsFile("input.txt");

    // part 1
    System.out.println(travel(jumps));
    
    // part 2
    System.out.println(travelOddly(jumps));

  }

  public static List<Integer> readJumpsFile(String filename) {
    List<Integer> jumps = new ArrayList<Integer>();

    try {
      BufferedReader buffReader = new BufferedReader(new FileReader(filename));
      String line; 

      while((line = buffReader.readLine()) != null) { 
        jumps.add(Integer.parseInt(line));
      }
    
    } catch(FileNotFoundException e) {
      System.out.println("File not found");
    } catch(IOException e) {
      System.out.println("Error reading file");
    }

    return jumps;
  }
  
  public static int travel(List<Integer> jumps) {
    int numJumps = 0;
    int pos = 0;
    int jump;

    while (pos < jumps.size()) {
      jump = jumps.get(pos);
      jumps.set(pos, jump + 1);
      pos = pos + jump;
      numJumps++;
    }

    return numJumps;
  }
  
  public static int travelOddly(List<Integer> jumps) {
    int numJumps = 0;
    int pos = 0;
    int jump;

    while (pos < jumps.size()) {
      jump = (int)jumps.get(pos);
      if (jump >= 3) {
        jumps.set(pos, jump - 1);
      } else {
        jumps.set(pos, jump + 1);
      }
      pos = pos + jump;
      numJumps++;
    }

    return numJumps;
  }
}
