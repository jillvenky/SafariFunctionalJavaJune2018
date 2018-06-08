package superiterable;

import students.Student;

import java.util.ArrayList;
import java.util.Arrays;

public class TryIt {
  public static void main(String[] args) {
    SuperIterable<Student> roster = new SuperIterable<>(Arrays.asList(
    Student.ofNameGradeCourses("Fred", 3.1F, "Math", "Physics"),
    Student.ofNameGradeCourses("Jim", 2.7F, "Art"),
    Student.ofNameGradeCourses("Sheila", 3.7F, "Math", "Physics", "Astrophysics")));

    roster
        .filter(s -> s.getGrade() < 3.5)
        .map(s -> s.getName() + " with a grade of " + s.getGrade())
        .forEach(s -> System.out.println("I have: " + s));

    roster
        .flatMap(s -> new SuperIterable<>(s.getCourses()))
        .distinct()
        .forEach(s -> System.out.println("Coursename: " + s));
  }
}
