package dev.m3s.programming2.homework2;

public class Course {
  private String name;
  private double credits;
  private int period;
  private int type;
  private int mandatory;

  public Course(String name, double credits, int period, int type, int mandatory) {
    this.name = name;
    this.credits = credits;
    this.period = period;
    this.type = type;
    this.mandatory = mandatory;
  }

  public String getName() {
    return name;
  }

  public double getCredits() {
    return credits;
  }

  public int getPeriod() {
    return period;
  }

  public int getType() {
    return type;
  }

  public int getMandatory() {
    return mandatory;
  }
}