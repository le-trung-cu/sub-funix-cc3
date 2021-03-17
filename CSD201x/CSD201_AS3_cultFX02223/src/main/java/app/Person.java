package app;

public class Person implements Comparable<Person> {
  String ID, name, birthplace;

  String bdob;

  // Constructor method
  public Person(String iD, String name, String birthplace, String bdob) {
    ID = iD;
    this.name = name;
    this.birthplace = birthplace;
    this.bdob = bdob;
  }

  public Person(String id) {
    this.ID = id;
  }

  // toString method for display
  @Override
  public String toString() {
    return String.format("%-10s %-10s %-20s %-10s", ID, name, bdob, birthplace);
  }

  // equals method (compare by ID) for insert to tree
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Person) {
      return ((Person) obj).ID.equals(this.ID);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public int compareTo(Person o) {
    return this.ID.compareTo(o.ID);
  }

  
}
