package students;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@FunctionalInterface
interface StudentCriterion {
  boolean test(Student s);
  static StudentCriterion negate(StudentCriterion crit) {
    return s -> !crit.test(s);
  }

  static StudentCriterion and(StudentCriterion first, StudentCriterion second) {
    return s -> first.test(s) && second.test(s);
  }

  static StudentCriterion or(StudentCriterion first, StudentCriterion second) {
    return s -> first.test(s) || second.test(s);
  }
}

public class School {
  public static List<Student> getStudentsByCriterion(
      Iterable<Student> ls, StudentCriterion criterion) {
    List<Student> out = new ArrayList<>();
    for (Student s : ls) {
      if (criterion.test(s)) {
        out.add(s);
      }
    }
    return out;
  }

  public static void showAll(Iterable<Student> ls) {
    for (Student s : ls) {
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
    StudentCriterion smartCriterion = Student.getSmartCriterion();
    showAll(getStudentsByCriterion(roster, smartCriterion));
    showAll(getStudentsByCriterion(roster, s -> s.getName().charAt(0) <= 'M'));

    StudentCriterion enthusiasmCriterion = Student.getEnthusiasmCriterion(2);
    showAll(getStudentsByCriterion(roster, enthusiasmCriterion));
    StudentCriterion unenthusiasticCriterion =
        StudentCriterion.negate(enthusiasmCriterion);
    showAll(getStudentsByCriterion(roster, unenthusiasticCriterion));

    StudentCriterion smartButNotEnthusiastic =
        StudentCriterion.and(smartCriterion, unenthusiasticCriterion);
    showAll(getStudentsByCriterion(roster, smartButNotEnthusiastic));
  }
}
