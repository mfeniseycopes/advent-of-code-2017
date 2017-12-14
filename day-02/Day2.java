import java.io.*;

public class Day2 {

  public static void main(String[] args) {
    int[][] sheetData = readSpreadsheet("./input.txt", 16, 16); 
    System.out.println(checksum(sheetData, 16, 16));
    System.out.println(divisibleSum(sheetData, 16, 16));
  }

  public static int[][] readSpreadsheet(String filename, int rows, int cols) {
    String[] lineNums;
    int[][] sheetData = new int[16][16];

    try {
      FileReader reader = new FileReader(filename);
      BufferedReader buffReader = new BufferedReader(reader);
      
      for (int i = 0; i < rows; i++) {
        lineNums = buffReader.readLine().split("\t");

        for (int j = 0; j < cols; j++) {
          sheetData[i][j] = Integer.parseInt(lineNums[j]); 
        }
      }
    } catch(FileNotFoundException ex) {
      System.out.println("Unable to open file");
    } catch(IOException ex) {
      System.out.println("Error reading file");
      // Or we could just do this:
      // ex.printStackTrace();
    }

    return sheetData;
  }

  public static int checksum(int[][] nums, int rows, int cols) {
    int min;
    int max;
    int sum = 0;

    for (int i = 0; i < rows; i++) {
      min = Integer.MAX_VALUE;
      max = Integer.MIN_VALUE;

      for (int j = 0; j < cols; j++) {
        if (nums[i][j] < min) {
          min = nums[i][j];
        } 

        if (nums[i][j] > max) {
          max = nums[i][j];
        }
      }

      sum += (max - min);
    }

    return sum;
  }

  public static int divisibleSum(int[][] nums, int rows, int cols) {
    int sum = 0;

    for (int i = 0; i < rows; i++) {
      sum += evenlyDivisibleValue(nums[i]); 
    }

    return sum;
  }

  public static int evenlyDivisibleValue(int[] nums) {
    for (int j = 0; j < nums.length; j++) {
      for (int k = j + 1; k < nums.length; k++) {
        if (nums[j] % nums[k] == 0) {
          return nums[j] / nums[k];
        } else if (nums[k] % nums[j] == 0) {
          return nums[k] / nums[j];
        }
      }
    }

    return 0;
  }
}
