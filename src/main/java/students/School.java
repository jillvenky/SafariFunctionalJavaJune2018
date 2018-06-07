package students;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class School {
  public static List<Student> getSmartStudents(List<Student> ls) {
    List<Student> out = new ArrayList<>();
    for (Student s : ls) {
      if (s.getGrade() > 3.0) {
        out.add(s);
      }
    }
    return out;
  }

  public static void showAll(List<Student> ls) {
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
    showAll(getSmartStudents(roster));
  }
}
