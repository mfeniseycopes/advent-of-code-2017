import java.io.*;
import java.util.*;

public class Day4 {
  
  public static void main(String[] args) {
    // part 1
    System.out.println(validPassphrases("input.txt")); 

    // part 2
    System.out.println(validSuperPassphrases("input.txt"));
  } 

  public static int validPassphrases(String filename) {
    int validPhrases = 0;

    try {
      BufferedReader buffReader = new BufferedReader(new FileReader(filename));
      String line;
      String[] words;
      Set<String> set;

      outerloop:
      while ((line = buffReader.readLine()) != null) {
        words = line.split(" ");
        set = new HashSet<String>(Arrays.asList(words));

        if (set.size() == words.length) validPhrases++;
      }
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    } catch (IOException e) {
      System.out.println("Error reading file");
    }

    return validPhrases;
  }

  public static String sortString(String inputString) {
    char tempArray[] = inputString.toCharArray();
    Arrays.sort(tempArray);
    return new String(tempArray);
  }

  public static int validSuperPassphrases(String filename) {
    int validPhrases = 0;

    try {
      BufferedReader buffReader = new BufferedReader(new FileReader(filename));
      String line;
      String[] words;
      Set<String> set;

      outerloop:
      while ((line = buffReader.readLine()) != null) {
        words = line.split(" ");
        // convert each string into alphabetically sorted string
        for (int i = 0; i < words.length; i++) {
          words[i] = sortString(words[i]);
        }
        set = new HashSet<String>(Arrays.asList(words));

        if (set.size() == words.length) validPhrases++;
      }
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    } catch (IOException e) {
      System.out.println("Error reading file");
    }

    return validPhrases; 
  }

}

