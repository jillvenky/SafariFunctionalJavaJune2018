package exceptions;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

interface ExFunction<E, F> {
  F apply(E e) throws Throwable;
}

public class Breakable {
  // Or define Either<E, T> E is success value, T is throwable
  public static <E, F> Function<E, Optional<F>> wrapAndHide(ExFunction<E, F> fn) {
    return e -> {
      try {
        return Optional.of(fn.apply(e));
      } catch (Throwable t) {
        t.printStackTrace();
        return Optional.empty();
      }
    };
  }

  public static void main(String[] args) {
    Arrays.asList("A.txt", "B.txt", "C.txt")
        .stream()
        .map(wrapAndHide(x -> Files.lines(Paths.get(x))))
        .peek(opt -> {
          if (!opt.isPresent()) System.out.println("oops, that broke");
        })
        .filter(Optional::isPresent)
        .flatMap(Optional::get)
        .forEach(System.out::println);
  }
}
