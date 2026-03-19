package dev.m3s.programming2.homework2;

import java.time.LocalDate;
import java.util.Random;

public class Student {
  private String firstName = ConstantValues.NO_NAME;
  private String lastName = ConstantValues.NO_NAME;
  private int id;
  private int startYear = LocalDate.now().getYear();
  private int graduationYear;
  private int degreeCount = 3;
  private Degree[] degrees;
  private String birthDate = ConstantValues.NO_BIRTHDATE;

  public Student() {
    this.id = getRandomId();
    this.degrees = new Degree[degreeCount];
    for (int i = 0; i < degreeCount; i++) {
      degrees[i] = new Degree();
    }
  }

  public Student(String lname, String fname) {
    this();
    if (fname != null) this.firstName = fname;
    if (lname != null) this.lastName = lname;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    if (firstName != null) {
      this.firstName = firstName;
    }
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    if (lastName != null) {
      this.lastName = lastName;
    }
  }

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    if (id >= ConstantValues.MIN_ID && id <= ConstantValues.MAX_ID) {
      this.id = id;
    }
  }

  public int getStartYear() {
    return startYear;
  }

  public void setStartYear(final int startYear) {
    int currentYear = LocalDate.now().getYear();
    if (startYear > 2000 && startYear <= currentYear) {
      this.startYear = startYear;
    }
  }

  public int getGraduationYear() {
    return graduationYear;
  }

  public String setGraduationYear(final int graduationYear) {
    if (!canGraduate()) {
      return "Check amount of required credits";
    }
    int currentYear = LocalDate.now().getYear();
    if (graduationYear < startYear || graduationYear > currentYear) {
      return "Check graduation year";
    }
    this.graduationYear = graduationYear;
    return "Ok";
  }

  public String getBirthDate() {
    return birthDate;
  }

  public String setBirthDate(String personId) {
    if (personId == null) return "No change";
    PersonID pid = new PersonID();
    String result = pid.setPersonID(personId);
    if (result.equals("Ok")) {
      this.birthDate = pid.getBirthDate();
      return this.birthDate;
    }
    return "No change";
  }

  public boolean hasGraduated() {
    return graduationYear > 0;
  }

  private boolean canGraduate() {
    return degrees[0].getCredits() >= ConstantValues.BACHELOR_CREDITS
        && degrees[1].getCredits() >= ConstantValues.MASTER_CREDITS
        && !degrees[0].getTitleOfThesis().equals(ConstantValues.NO_TITLE)
        && !degrees[1].getTitleOfThesis().equals(ConstantValues.NO_TITLE);
  }

  public int getStudyYears() {
    int currentYear = LocalDate.now().getYear();
    if (hasGraduated()) {
      return graduationYear - startYear;
    }
    return currentYear - startYear;
  }

  private int getRandomId() {
    Random r = new Random();
    return r.nextInt(ConstantValues.MAX_ID - ConstantValues.MIN_ID + 1) + ConstantValues.MIN_ID;
  }

  public void setDegreeTitle(final int i, String dName) {
    if (i >= 0 && i < degreeCount && dName != null) {
      degrees[i].setDegreeTitle(dName);
    }
  }

  public boolean addCourse(final int i, StudentCourse course) {
    if (i >= 0 && i < degreeCount && course != null) {
      return degrees[i].addStudentCourse(course);
    }
    return false;
  }

  public int addCourses(final int i, StudentCourse[] courses) {
    if (i < 0 || i >= degreeCount || courses == null) return 0;
    int added = 0;
    for (StudentCourse course : courses) {
      if (course != null && degrees[i].addStudentCourse(course)) {
        added++;
      }
    }
    return added;
  }

  public void printCourses() {
    for (int i = 0; i < degreeCount; i++) {
      if (degrees[i] != null) {
        degrees[i].printCourses();
      }
    }
  }

  public void printDegrees() {
    for (int i = 0; i < degreeCount; i++) {
      if (degrees[i] != null) {
        System.out.println(degrees[i]);
      }
    }
  }

  public void setTitleOfThesis(final int i, String title) {
    if (i >= 0 && i < degreeCount && title != null) {
      degrees[i].setTitleOfThesis(title);
    }
  }

  @Override
  public String toString() {
    String status = hasGraduated()
        ? "The student has graduated in " + graduationYear
        : "The student has not graduated, yet";
    String studyYearsStr = hasGraduated()
        ? "(studies lasted for " + getStudyYears() + " years)"
        : "(studies have lasted for " + getStudyYears() + " years)";
    String birthDateStr = birthDate.equals(ConstantValues.NO_BIRTHDATE)
        ? "\"" + birthDate + "\""
        : birthDate;

    double bachelorCredits = degrees[0].getCredits();
    double masterCredits = degrees[1].getCredits();
    double totalCredits = bachelorCredits + masterCredits;

    String bachelorCreditsStr = bachelorCredits >= ConstantValues.BACHELOR_CREDITS
        ? String.format("Total bachelor credits completed (%.1f/%.1f)",
            bachelorCredits, ConstantValues.BACHELOR_CREDITS)
        : String.format("Missing bachelor credits %.1f (%.1f/%.1f)",
            ConstantValues.BACHELOR_CREDITS - bachelorCredits,
            bachelorCredits, ConstantValues.BACHELOR_CREDITS);

    String masterCreditsStr = masterCredits >= ConstantValues.MASTER_CREDITS
        ? String.format("Total master's credits completed (%.1f/%.1f)",
            masterCredits, ConstantValues.MASTER_CREDITS)
        : String.format("Missing master's credits %.1f (%.1f/%.1f)",
            ConstantValues.MASTER_CREDITS - masterCredits,
            masterCredits, ConstantValues.MASTER_CREDITS);

    return "Student id: " + id + "\n"
        + "\tFirst name: " + firstName + ", Last name: " + lastName + "\n"
        + "\tDate of birth: " + birthDateStr + "\n"
        + "\tStatus: " + status + "\n"
        + "\tStart year: " + startYear + " " + studyYearsStr + "\n"
        + "\tTotal credits: " + totalCredits + "\n"
        + "\tBachelor credits: " + bachelorCredits + "\n"
        + "\t\t" + bachelorCreditsStr + "\n"
        + "\t\tTitle of BSc Thesis: \"" + degrees[0].getTitleOfThesis() + "\"\n"
        + "\tMaster credits: " + masterCredits + "\n"
        + "\t\t" + masterCreditsStr + "\n"
        + "\t\tTitle of MSc Thesis: \"" + degrees[1].getTitleOfThesis() + "\"";
  }
}
