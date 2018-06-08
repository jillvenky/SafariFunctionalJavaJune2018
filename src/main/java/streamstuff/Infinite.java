package streamstuff;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Infinite {
  public static void main(String[] args) {
//    Stream.generate(() -> Math.random())
////        .mapToDouble...
//        .forEach(x -> System.out.println(x));

    IntStream.iterate(0, x -> x + 1)
        .map(x -> x * 2)
        .mapToObj(x -> "The number is " + x)
        .forEach(x -> System.out.println(x));
  }
}
