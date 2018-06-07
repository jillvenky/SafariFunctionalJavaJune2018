package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class School {
  public static <E> List<E> getByCriterion(
      Iterable<E> ls, Predicate<E> criterion) {
    List<E> out = new ArrayList<>();
    for (E s : ls) {
      if (criterion.test(s)) {
        out.add(s);
      }
    }
    return out;
  }

  public static <E> void showAll(Iterable<E> ls) {
    for (E s : ls) {
      System.out.println("> " + s);
    }
    System.out.println("------------------------------");
  }

  public static void main(String[] args) {
    List<Student> roster = new ArrayList<>();
    roster.add(Student.ofNameGradeCourses("Fred", 3.1F, "Math", "Physics"));
    roster.add(Student.ofNameGradeCourses("Jim", 2.7F, "Art"));
    roster.add(Student.ofNameGradeCourses("Sheila", 3.7F, "Math", "Physics", "Astrophysics"));
    showAll(roster);
    Predicate<Student> smartCriterion = Student.getSmartCriterion();
    Predicate<Student> enthusiasmCriterion = Student.getEnthusiasmCriterion(2);

    showAll(getByCriterion(roster, smartCriterion));
    showAll(getByCriterion(roster, s -> s.getName().charAt(0) <= 'M'));

    showAll(getByCriterion(roster, enthusiasmCriterion));

    Predicate<Student> unenthusiasticCriterion =
        enthusiasmCriterion.negate();
    showAll(getByCriterion(roster, unenthusiasticCriterion));

    Predicate<Student> smartButNotEnthusiastic =
        smartCriterion.and(enthusiasmCriterion.negate());
    showAll(getByCriterion(roster, smartButNotEnthusiastic));

    List<String> names = Arrays.asList("Fred", "Jim", "Sheila", "William");
    showAll(getByCriterion(names, s -> s.length() > 4));

    showAll(roster);
    roster.sort((s1, s2) -> Float.compare(s1.getGrade(), s2.getGrade()));
    showAll(roster);
  }
}
