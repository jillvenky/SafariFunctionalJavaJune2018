package students;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface StudentCriterion {
  boolean test(Student s);
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

//  public static List<Student> getSmartStudents(Iterable<Student> ls, float threshold) {
//    List<Student> out = new ArrayList<>();
//    for (Student s : ls) {
//      if (s.getGrade() > threshold) {
//        out.add(s);
//      }
//    }
//    return out;
//  }
//
//  public static List<Student> getEnthusiasticStudents(
//      Iterable<Student> ls, int threshold) {
//    List<Student> out = new ArrayList<>();
//    for (Student s : ls) {
//      if (s.getCourses().size() > threshold) {
//        out.add(s);
//      }
//    }
//    return out;
//  }
//
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
//    showAll(getSmartStudents(roster, 3.5F));
    showAll(getStudentsByCriterion(roster, Student.getSmartCriterion()));
    showAll(getStudentsByCriterion(roster, new Student.EnthusiasmCriterion()));
  }
}
