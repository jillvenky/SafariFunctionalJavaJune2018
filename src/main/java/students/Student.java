package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Student {
  private final String name;
  private final float grade;
  private final List<String> courses;

  private Student(String name, float grade, List<String> courses) {
    validate(name, grade, courses);
    this.name = name;
    this.grade = grade;
    this.courses = Collections.unmodifiableList(new ArrayList<>(courses));
  }

  public static Student ofNameGradeCourses(String name, float grade, String... courses) {
    return new Student(name, grade, Arrays.asList(courses));
  }

  public static void validate(String name, float grade, List<String> courses) {
    if (name == null || grade < 0 || grade > 4.0)
      throw new IllegalArgumentException("Bad student data");
  }

  public String getName() {
    return name;
  }

  public float getGrade() {
    return grade;
  }

  public List<String> getCourses() {
    return courses;
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", grade=" + grade +
        ", courses=" + courses +
        '}';
  }

  static class SmartCriterion implements StudentCriterion {
    @Override
    public boolean test(Student s) {
//      return s.getGrade() > 3.0;
      return s.grade > 3.0;
    }
  }

  static class EnthusiasmCriterion implements StudentCriterion {
    @Override
    public boolean test(Student s) {
//      return s.getCourses().size() > 2;
      return s.courses.size() > 2;
    }
  }
}
