package superiterable;

import students.Student;

import java.util.Arrays;
import java.util.List;

public class TryStream {
  public static void main(String[] args) {
    List<Student> roster = Arrays.asList(
    Student.ofNameGradeCourses("Fred", 3.1F, "Math", "Physics"),
    Student.ofNameGradeCourses("Jim", 2.7F, "Art"),
    Student.ofNameGradeCourses("Sheila", 3.7F, "Math", "Physics", "Astrophysics"));

    roster.stream()
        .filter(s -> s.getGrade() < 3.5)
        .map(s -> s.getName() + " with a grade of " + s.getGrade())
        .forEach(s -> System.out.println("I have: " + s));

    roster.stream()
        .flatMap(s -> s.getCourses().stream())
        .distinct()
        .forEach(s -> System.out.println("Coursename: " + s));
  }
}
