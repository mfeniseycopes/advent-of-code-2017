import java.io.*;
import java.util.*;

public class Day6 {

  public static void main(String[] args) {
    int[] puzzle = readPuzzle("input.txt");

    // part 1
    System.out.println(cycleUntilRepeat(puzzle));
  
  }

  public static int[] readPuzzle(String filename) {
    int[] puzzle = {};

    try {
      BufferedReader buffReader = new BufferedReader(new FileReader(filename));
      String[] puzzleStrs = buffReader.readLine().split("\t");
      puzzle = new int[puzzleStrs.length];

      for (int i = 0; i < puzzleStrs.length; i++) {
        puzzle[i] = Integer.parseInt(puzzleStrs[i]);
      }

    } catch(FileNotFoundException e) {
      System.out.println("File not found");
    } catch(IOException e) {
      System.out.println("Error reading file");
    }

    return puzzle;
  }
  
  public static String[] toStringArray(int[] ints) {
    String[] strs = new String[ints.length];

    for (int i = 0; i < ints.length; i++) {
      strs[i] = String.valueOf(ints[i]);
    }

    return strs;
  }

  public static int cycleUntilRepeat(int[] banks) {
    int cycles = 0;
    String  currentConfig = String.join(",", toStringArray(banks));
    Set<String> previousConfigs = new HashSet<String>();

    while (!previousConfigs.contains(currentConfig)) {
      previousConfigs.add(currentConfig);
      redistributeCycle(banks);
      currentConfig = String.join(",", toStringArray(banks));
      cycles++; 
    }

    return cycles;
  }

  public static void redistributeCycle(int[] banks) {
    // find max
    int maxBankIdx = 0;
    int maxMemory = 0;

    for (int i = 0; i < banks.length; i++) {
      if (banks[i] > banks[maxBankIdx]) {
        maxBankIdx = i;
      }
    } 

    // reallocate
    maxMemory = banks[maxBankIdx];
    banks[maxBankIdx] = 0;

    for (int i = 0; i < maxMemory; i++) {
      banks[(maxBankIdx + 1 + i) % banks.length]++;
    }
  }

}
