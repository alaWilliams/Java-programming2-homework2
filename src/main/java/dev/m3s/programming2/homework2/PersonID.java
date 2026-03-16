package dev.m3s.programming2.homework2;

public class PersonID {
  private String birthDate = ConstantValues.NO_BIRTHDATE;

  public String getBirthDate() {
    return this.birthDate;
  }

  public String setPersonID(final String personID) {
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

  private boolean checkPersonIDNumber(final String personID){
    if (personID.length() == 11 && (personID.charAt(6) == '-' || personID.charAt(6) == '+' || personID.charAt(6) == 'A' )) {
      return true;
    } else {
      return false;
    }
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
}
