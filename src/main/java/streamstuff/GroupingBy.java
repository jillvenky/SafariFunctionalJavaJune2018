package streamstuff;

import students.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingBy {
  public static String getGrade(Student s) {
    float grade = s.getGrade();
    if (grade > 3.5) return "A";
    else if (grade > 3.1) return "B";
    else if (grade > 2.8) return "C";
    else return "D";
  }

  public static void main(String[] args) {
    List<Student> roster = Arrays.asList(
    Student.ofNameGradeCourses("Fred", 3.1F, "Math", "Physics"),
    Student.ofNameGradeCourses("Jim", 2.7F, "Art"),
    Student.ofNameGradeCourses("Jim2", 3.7F, "Art"),
    Student.ofNameGradeCourses("Jim3", 3.2F, "Art"),
    Student.ofNameGradeCourses("Sheila", 3.7F, "Math", "Physics", "Astrophysics"));

    Map<String, List<Student>> map = roster.stream()
        .collect(Collectors.groupingBy(GroupingBy::getGrade));

    map.forEach((k, v) -> System.out.println("Students with grade " + k
      + " are " + v));

    roster.stream()
        .collect(Collectors.groupingBy(
            GroupingBy::getGrade, Collectors.counting()))
        .forEach((k, v) -> System.out.println(
            v + " students scored a grade " + k));
  }
}
