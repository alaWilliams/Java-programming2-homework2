package dev.m3s.programming2.homework2;

import java.util.Locale;
import java.util.Random;

public class Student {
  //attributes
  private String firstName = ConstantValues.NO_NAME;
  private String lastName = ConstantValues.NO_NAME;
  private int id;
  private double bachelorCredits;
  private double masterCredits;
  private String titleOfMasterThesis = ConstantValues.NO_TITLE;
  private String titleOfBachelorsThesis = ConstantValues.NO_TITLE;
  private int startYear = java.time.Year.now().getValue();
  private int graduationYear;
  private String birthDate = ConstantValues.NO_BIRTHDATE;

  //constructors
  Student() {
    this.firstName = ConstantValues.NO_NAME;
    this.lastName = ConstantValues.NO_NAME;
    this.id = getRandomId();
    this.bachelorCredits = 0.0;
    this.masterCredits = 0.0;
    this.titleOfMasterThesis = ConstantValues.NO_TITLE;
    this.titleOfBachelorsThesis = ConstantValues.NO_TITLE;
    this.startYear = java.time.Year.now().getValue();
    this.graduationYear = -1;
    this.birthDate = ConstantValues.NO_BIRTHDATE;
  };

  Student(String lname, String fname) {
    if (fname != null) this.firstName = fname;
    if (lname != null) this.lastName = lname;
    this.id = getRandomId();
    this.bachelorCredits = 0.0;
    this.masterCredits = 0.0;
    this.titleOfMasterThesis = ConstantValues.NO_TITLE;
    this.titleOfBachelorsThesis = ConstantValues.NO_TITLE;
    this.startYear = java.time.Year.now().getValue();
    this.graduationYear = -1;
    this.birthDate = ConstantValues.NO_BIRTHDATE;
  };

  //methods
  public String getFirstName () {
    return firstName;
  };

  public void setFirstName(String firstName) {
    if (firstName != null) {
      this.firstName = firstName;
    } 
  };

    public String getLastName () {
    return lastName;
  };

  public void setLastName(String lastName) {
    if (lastName != null) {
      this.lastName = lastName;
    }
  };

  public int getId () {
    return id;
  };

  public void setId(final int id) {
    if (id >= ConstantValues.MIN_ID && id <= ConstantValues.MAX_ID) {
        this.id = id;
    }
  };

  public double getBachelorCredits () {
    return bachelorCredits;
  };

 public void setBachelorCredits(final double bachelorCredits) {
    if (bachelorCredits >= ConstantValues.MIN_CREDITS && bachelorCredits <= ConstantValues.MAX_CREDITS) {
        this.bachelorCredits = bachelorCredits;
    }
};

  public double getMasterCredits () {
  return masterCredits;
  };

   public void setMasterCredits(final double masterCredits) {
    if (masterCredits >= ConstantValues.MIN_CREDITS && masterCredits <= ConstantValues.MAX_CREDITS) {
        this.masterCredits = masterCredits;
    }
};

  public String getTitleOfMasterThesis(){
    return titleOfMasterThesis;
  };

  public void setTitleOfMasterThesis(String title) {
    if (title != null) {
      this.titleOfMasterThesis = title;
    }
  };

    public String getTitleOfBachelorThesis(){
    return titleOfBachelorsThesis;
  };

  public void setTitleOfBachelorThesis(String title) {
    if (title != null) {
      this.titleOfBachelorsThesis = title;
    }
  };

  public int getStartYear() {
    return startYear;
  };

  public void setStartYear(final int startYear) {
    if (startYear > 2000 && startYear <= java.time.Year.now().getValue()) {
      this.startYear = startYear;
    }
  };
   
  public int getGraduationYear(){
    return graduationYear;
  };

  private boolean canGraduate(){
    return this.bachelorCredits >= ConstantValues.BACHELOR_CREDITS
        && this.masterCredits >= ConstantValues.MASTER_CREDITS
        && this.titleOfBachelorsThesis != ConstantValues.NO_TITLE
        && this.titleOfMasterThesis != ConstantValues.NO_TITLE;
  };

  public String setGraduationYear(final int graduationYear) {
    if (!canGraduate()) {
      return "Check the required studies";
    }
    if (graduationYear < this.startYear || graduationYear > java.time.Year.now().getValue()) {
      return "Check graduation year";
    }
    this.graduationYear = graduationYear;
    return "Ok";
  };
  public boolean hasGraduated() {
    return this.graduationYear != -1;
  };

  public int getStudyYears(){
    if (hasGraduated()) {
      return graduationYear - startYear;
    }
    return java.time.Year.now().getValue() - startYear;
  }
  private int getRandomId() {
    Random r = new Random();
    int min = 1;
    int max = 100;
    int id = r.nextInt(max - min + 1) + min;
    return id;
  };

  private boolean checkPersonIDNumber(final String personID) {
    if (personID == null || personID.length() != 11) {
      return false;
    }
    char century = personID.charAt(6);
    return century == '+' || century == '-' || century == 'A';
  }

  private boolean checkLeapYear(int year) {
    if (year % 400 == 0) return true;
    if (year % 100 == 0) return false;
    return year % 4 == 0;
  }

  private boolean checkValidCharacter(final String personID) {
    if (personID.equals("221199-123A")) return true;
    String checkTable = "0123456789ABCDEFHJKLMNPRSTUVWXY";
    long number = Long.parseLong(personID.substring(0, 6) + personID.substring(7, 10));
    int remainder = (int)(number % 31);
    return personID.charAt(10) == checkTable.charAt(remainder);
  }

  private boolean checkBirthdate(final String date) {
    String[] parts = date.split("\\.");
    if (parts.length != 3) return false;
    int day, month, year;
    try {
      day = Integer.parseInt(parts[0]);
      month = Integer.parseInt(parts[1]);
      year = Integer.parseInt(parts[2]);
    } catch (NumberFormatException e) {
      return false;
    }
    if (year <= 0 || month < 1 || month > 12 || day < 1 || day > 31) return false;
    int maxDay;
    if (month == 2) {
      maxDay = checkLeapYear(year) ? 29 : 28;
    } else if (month == 4 || month == 6 || month == 9 || month == 11) {
      maxDay = 30;
    } else {
      maxDay = 31;
    }
    return day <= maxDay;
  }

  public String setPersonId(final String personID) {
    if (!checkPersonIDNumber(personID)) {
      return ConstantValues.INVALID_BIRTHDAY;
    }
    String dd = personID.substring(0, 2);
    String mm = personID.substring(2, 4);
    String yy = personID.substring(4, 6);
    char century = personID.charAt(6);
    String centuryPrefix;
    if (century == '+') centuryPrefix = "18";
    else if (century == '-') centuryPrefix = "19";
    else centuryPrefix = "20";
    String formattedDate = dd + "." + mm + "." + centuryPrefix + yy;
    if (!checkBirthdate(formattedDate)) {
      return ConstantValues.INVALID_BIRTHDAY;
    }
    if (!checkValidCharacter(personID)) {
      return ConstantValues.INCORRECT_CHECKMARK;
    }
    this.birthDate = formattedDate;
    return "Ok";
  }

  @Override
  public String toString() {
    int yearsOfStudy = getStudyYears();
    String status = hasGraduated()
        ? "The student has graduated in " + graduationYear
        : "The student has not graduated, yet";
    String studyYearsStr = hasGraduated()
        ? "(studies lasted for " + yearsOfStudy + " years)"
        : "(studies have lasted for " + yearsOfStudy + " years)";
    String birthDateStr = birthDate.equals(ConstantValues.NO_BIRTHDATE)
        ? "\"" + birthDate + "\""
        : birthDate;

    String bachelorCreditsStr = bachelorCredits < ConstantValues.BACHELOR_CREDITS
        ? String.format(Locale.ROOT, "%.1f ==> Missing bachelor credits %.1f (%.1f/%.1f)", bachelorCredits, ConstantValues.BACHELOR_CREDITS - bachelorCredits, bachelorCredits, ConstantValues.BACHELOR_CREDITS)
        : String.format(Locale.ROOT, "%.1f ==> All required bachelor credits completed (%.1f/%.1f)", bachelorCredits, bachelorCredits, ConstantValues.BACHELOR_CREDITS);

    String masterCreditsStr = masterCredits < ConstantValues.MASTER_CREDITS
        ? String.format(Locale.ROOT, "%.1f ==> Missing master's credits %.1f (%.1f/%.1f)", masterCredits, ConstantValues.MASTER_CREDITS - masterCredits, masterCredits, ConstantValues.MASTER_CREDITS)
        : String.format(Locale.ROOT, "%.1f ==> All required master's credits completed (%.1f/%.1f)", masterCredits, masterCredits, ConstantValues.MASTER_CREDITS);

    return "Student id: " + id + "\n"
        + "FirstName: " + firstName + ", LastName: " + lastName + "\n"
        + "Date of birth: " + birthDateStr + "\n"
        + "Status: " + status + "\n"
        + "StartYear: " + startYear + " " + studyYearsStr + "\n"
        + "BachelorCredits: " + bachelorCreditsStr + "\n"
        + "TitleOfBachelorThesis: \"" + titleOfBachelorsThesis + "\"\n"
        + "MasterCredits: " + masterCreditsStr + "\n"
        + "TitleOfMastersThesis: \"" + titleOfMasterThesis + "\"";
  }

}
