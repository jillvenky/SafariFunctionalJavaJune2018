package streamstuff;

import java.util.OptionalDouble;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

class Average {
  private double sum;
  private long count;

  public Average() {}

  public void include(double d) {
    sum += d;
    count++;
  }

  public void merge(Average other) {
    sum += other.sum;
    count += other.count;
  }

  public OptionalDouble get() {
    if (count == 0) {
      return OptionalDouble.empty();
    } else {
      return OptionalDouble.of(sum / count);
    }
  }
}

public class Averager {
  public static void main(String[] args) {
    long start = System.nanoTime();
    DoubleStream.generate(
        () -> ThreadLocalRandom.current().nextDouble(-Math.PI, +Math.PI))
        .parallel()
//        .unordered() // generate is already unordered
        .limit(8_000_000)
        .map(x -> Math.sin(x))
        .map(x -> Math.asin(x))
        .map(x -> Math.sin(x))
        .collect(() -> new Average(),
            (b, i) -> b.include(i),
            (bf, bi) -> bf.merge(bi))
        .get()
        .ifPresent(a -> System.out.println("Average is " + a));
    long totalTime = System.nanoTime() - start;
    System.out.printf("Time taken: %9.6f\n", totalTime / 1_000_000_000.0 );
  }
}
