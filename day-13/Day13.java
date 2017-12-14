import java.util.*;
import java.io.*;

public class Day13 {

  public static void main(String[] args) {
    List<Layer> layers = readInput("input.txt");

    // part 1
    System.out.println(calculateSeverity(layers));

    // part 2
    System.out.println(minimumDelay(layers));
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
            layers.add(null);
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

  public static void moveScanners(List<Layer> layers, int pos) {
    Layer layer;

    for (int i = pos; i < layers.size(); i++) {
      layer = layers.get(i);

      if (layer != null) {
        layer.pos += layer.shift; 
      
        if (layer.pos == 1 || layer.pos == layer.range) {
          layer.shift = (-1 * layer.shift); 
        }
      }
    }
  }

  public static void moveN(Layer layer, int n) {
    if (layer == null) return;

    int offset = n % (layer.range - 1);
    boolean fromTop = (n / (layer.range - 1) % 2) == 0;
    
    if (fromTop) {
      layer.pos = offset + 1;
    } else {
      layer.pos = layer.range - offset;
      layer.shift = -1; 
    }
  }

  public static boolean caught(List<Layer> layers, int delay) {
    int pos = 0;

    for (int i = 0; i < layers.size(); i++) {
      Layer layer = layers.get(i);
      if (layer != null) {
        layer.pos = 1;
        layer.shift = 1;
      }
    }

    for (int i = 0; i < layers.size(); i++) {
      moveN(layers.get(i), delay);
    }

    while (pos < layers.size()) {
      Layer layer = layers.get(pos);

      if (layer != null && layer.pos == 1) {
        return true;
      }

      moveScanners(layers, pos);
      pos++;
    }

    return false;
  }

  public static int calculateSeverity(List<Layer> layers) {
    int pos = 0;
    int severity = 0;

    for (int i = 0; i < layers.size(); i++) {
      Layer layer = layers.get(i);
      if (layer != null) {
        layer.pos = 1;
        layer.shift = 1;
      }
    }

    while (pos < layers.size()) {
      Layer layer = layers.get(pos);

      if (layer != null && layer.pos == 1) {
        severity += (layer.depth * layer.range);
      }

      moveScanners(layers, pos);
      pos++;
    }

    return severity;
  }

  public static int minimumDelay(List<Layer> layers) {
    int delay = 0;

    while (caught(layers, delay)) {
      delay++;
    }

    return delay; 
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
