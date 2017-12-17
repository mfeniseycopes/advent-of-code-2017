import java.util.*;

public class Day17 {
  
  public static void main(String[] args) {
    
    // part 1
    int times = 2017;
    int stepSize = 344; 
    List<Integer> circularBuffer = generateSpinlock(times, stepSize);
    int valueAfter = getNextValue(times, circularBuffer);
    System.out.println(valueAfter);

    // part 2
    int times2 = 50000000;
    Element circularBuffer2 = generateSpinlock2(times2, stepSize);
    int valueAfter2 = circularBuffer2.next.value;
    System.out.println(valueAfter2);

  }

  public static List<Integer> generateSpinlock(int times, int stepSize) {
    List<Integer> circularBuffer = new ArrayList<Integer>();
    circularBuffer.add(0);
  
    int currentPos = 0;

    for (int nextValue = 1; nextValue <= times; nextValue++) {
      // move stepsize times
      currentPos = (currentPos + stepSize) % circularBuffer.size();
      // pick position to add nextValue
      int insertPos = currentPos + 1;
      
      // insert position may be greater than available indices
      if (insertPos < circularBuffer.size()) {
        circularBuffer.add(currentPos + 1, nextValue);
        currentPos = insertPos;
      } else {
        circularBuffer.add(nextValue);
        currentPos = circularBuffer.size() - 1;
      }
    }

    return circularBuffer;
  }

  public static Element generateSpinlock2(int times, int stepSize) {
    Element circularBuffer = new Element(0, null);
    circularBuffer.next = circularBuffer;
    Element currentElement = circularBuffer;
  
    for (int nextValue = 1; nextValue <= times; nextValue++) {
      // move stepsize times
      for (int s = 0; s < stepSize; s++) {
        currentElement = currentElement.next;
      }
      
      // create next element
      Element nextElement = new Element(nextValue, currentElement.next);
      currentElement.next = nextElement;
      
      currentElement = nextElement;
    }

    return circularBuffer;
  }  

  public static int getNextValue(int value, List<Integer> circularBuffer) {
    int index = circularBuffer.indexOf(value); 
    return circularBuffer.get((index + 1) % circularBuffer.size());
  }

}

class Element {
  public int value;
  public Element next;

  public Element (int _value, Element _next) {
    value = _value;
    next = _next;
  }
}
