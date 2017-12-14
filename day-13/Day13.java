import java.util.*;
import java.io.*;

public class Day13 {

  public static void main(String[] args) {
    List<Layer> layers = readInput("input.txt");

    // part 1
    System.out.println(calculateSeverity(layers));
  
  }

  public static List<Layer> readInput(String filename) {
    List<Layer> layers = new ArrayList<Layer>();

    try {
      BufferedReader reader = new BufferedReader(new FileReader(filename)); 
      String line;
      int depth, range;

      while((line = reader.readLine()) != null) {
        String[] split = line.split(": ");
        depth = Integer.parseInt(split[0]);
        range = Integer.parseInt(split[1]);
        if (depth > layers.size()) {
          for (int i = layers.size(); i < depth; i++) {
            layers.add(new Layer(i, 0));
          }
        }
        layers.add(new Layer(depth, range));
      }
    } catch(FileNotFoundException e) {
      System.out.println("Unable to open file"); 
    } catch(IOException e) {
      System.out.println("Error reading file"); 
    }

    return layers;
  }

  public static void moveScanners(List<Layer> layers) {
    Layer layer;

    for (int i = 0; i < layers.size(); i++) {
      layer = layers.get(i);

      layer.pos += layer.shift; 
      
      if (layer.pos == 1 || layer.pos == layer.range) {
        layer.shift = -1 * layer.shift; 
      }

      if (layer.pos == 0 || layer.pos > layer.range) {
        layer.pos = 1;
      }
    }
  }

  public static int calculateSeverity(List<Layer> layers) {
    int pos = 0;
    int severity = 0;
    Layer layer;

    while (pos < layers.size()) {
      layer = layers.get(pos);

      if (layer.pos == 1) {
        severity += (layer.depth * layer.range);
      }

      moveScanners(layers);
      pos++;
    }
  
    return severity;
  }
}

class Layer {
  public int depth;
  public int range;
  public int pos;
  public int shift;

  public Layer(int _depth, int _range) {
    depth = _depth;
    range = _range; 
    pos = 1;
    shift = 1;
  }
}
