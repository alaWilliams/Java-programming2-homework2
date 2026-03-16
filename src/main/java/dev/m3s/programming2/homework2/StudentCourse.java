package dev.m3s.programming2.homework2;

import java.time.LocalDate;

public class StudentCourse {
  private Course course;
  private int gradeNum;
  private int yearCompleted;

  public StudentCourse() {
  }

  public StudentCourse(Course course, final int gradeNum, final int yearCompleted) {
    this.course = course;
    setGrade(gradeNum);
    setYear(yearCompleted);
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public int getGradeNum() {
    return gradeNum;
  }

  protected void setGrade(int gradeNum) {
    if (checkGradeValidity(gradeNum)) {
      this.gradeNum = gradeNum;
      if (yearCompleted == 0) {
        yearCompleted = LocalDate.now().getYear();
      }
    }
  }

  private boolean checkGradeValidity(final int gradeNum) {
    if (course != null && course.isNumericGrade()) {
      return gradeNum >= ConstantValues.MIN_GRADE && gradeNum <= ConstantValues.MAX_GRADE;
    } else {
      return gradeNum == ConstantValues.GRADE_FAILED || gradeNum == ConstantValues.GRADE_ACCEPTED;
    }
  }

  public boolean isPassed() {
    return gradeNum != 0 && gradeNum != ConstantValues.GRADE_FAILED;
  }

  public int getYear() {
    return yearCompleted;
  }

  public void setYear(final int year) {
    int currentYear = LocalDate.now().getYear();
    if (year > 2000 && year <= currentYear) {
      this.yearCompleted = year;
    }
  }

  @Override
  public String toString() {
    String gradeDisplay;
    if (gradeNum == 0) {
      gradeDisplay = "\"Not graded\"";
    } else if (course != null && course.isNumericGrade()) {
      gradeDisplay = String.valueOf(gradeNum);
    } else {
      gradeDisplay = String.valueOf((char) gradeNum);
    }
    return course.toString() + " Year: " + yearCompleted + ", Grade: " + gradeDisplay + ".]";
  }
}
