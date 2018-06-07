package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class School {
  public static <E> List<E> getByCriterion(
      Iterable<E> ls, Criterion<E> criterion) {
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
    Criterion<Student> smartCriterion = Student.getSmartCriterion();
    Criterion<Student> enthusiasmCriterion = Student.getEnthusiasmCriterion(2);

    showAll(getByCriterion(roster, smartCriterion));
    showAll(getByCriterion(roster, s -> s.getName().charAt(0) <= 'M'));

    showAll(getByCriterion(roster, enthusiasmCriterion));

    Criterion<Student> unenthusiasticCriterion =
        enthusiasmCriterion.negate();
    showAll(getByCriterion(roster, unenthusiasticCriterion));

    Criterion<Student> smartButNotEnthusiastic =
        smartCriterion.and(enthusiasmCriterion.negate());
    showAll(getByCriterion(roster, smartButNotEnthusiastic));

    List<String> names = Arrays.asList("Fred", "Jim", "Sheila", "William");
    showAll(getByCriterion(names, s -> s.length() > 4));
  }
}
