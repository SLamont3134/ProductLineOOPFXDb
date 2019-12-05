package lamont;

/**
 * The program is required to create an audit trail of the lamont.production line so that it records
 * which employee recorded lamont.production. To accomplish this you will need to create a class and
 * tab named Employee that will allow the user to input their full name and then create a user id of
 * their first name, a period, and then their surname, an email address of their first initial and
 * last name.
 */
public class Employee {

  public StringBuilder getName() {
    return name;
  }

  public void setName(StringBuilder name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  // StringBuilder name;
  StringBuilder name;

  // String username;
  String username;

  // String password;
  String password;

  // String email;
  String email;

  // The class will have the following methods defined:

  /**
   * setUsername will set the username field to the first initial of the first name and then the
   * last name, all lowercase.
   *
   * @param name String, the name of the Employee.
   */
  // private void setUsername
  private void setUsername(String name) {
    StringBuilder tempSB = new StringBuilder();
    tempSB.append(name.substring(0, 1).toLowerCase());
    int i = 0;
    while (!(name.charAt(i) == ' ')) {
      i++;
    }
    System.out.println(name.charAt(i));
    tempSB.append(name.substring(i + 1).toLowerCase());
    this.username = tempSB.toString();
  }

  // private boolean checkName
  private boolean checkName(String name) {
    boolean result = false;
    for (int i = 0; i < name.length(); i++) {
      if (name.charAt(i) == ' ') {
        result = true;
      }
    }
    return result;
  }

  /**
   * setEmail will set the email field to the first name, then a period, then the last name (all
   * lowercase) followed by @oracleacademy.Test
   *
   * @param name String, the first and last name of the employee.
   */
  // private void setEmail
  private void setEmail(String name) {
    StringBuilder tempSB = new StringBuilder();
    int i = 0;
    while (name.charAt(i) != ' ') {
      i++;
    }
    tempSB.append(name.substring(0, i).toLowerCase());
    tempSB.append('.');
    tempSB.append(name.substring(i + 1, name.length()).toLowerCase());
    tempSB.append("@oracleacademy.Test");
    this.email = tempSB.toString();
  }

  /**
   * The constructor will call isValidPassword. If the password is valid (containing a lowercase
   * letter, uppercase letter, and a special character) the password field gets set to the supplied
   * password. If the password is invalid, the password field gets set to "pw".
   */
  // private boolean isValidPassword
  private boolean isValidPassword(String password) {
    // checks to see if the password is all lower case
    boolean lwrCase = !(password.equals(password.toUpperCase()));
    // checks to see if the password is all upper case
    boolean upprCase = !(password.equals(password.toLowerCase()));
    // checks to see  if the password doesn't have at least one special character in it
    boolean spclCase = !(password.matches("[A-Za-z0-9 ]*"));
    if (lwrCase && upprCase && spclCase) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * The constructor will accept a String for name (firstname and surname) and String for password.
   * The constructor will call checkName to check if the name contains a space. If it does, it will
   * call setUsername and setEmail, passing the name in to both. If it doesn't contain a space, set
   * the username to "default" and the email to "user@oracleacademy.Test"
   *
   * @param name String, first and last name of employee separated by a space.
   * @param password String, the password to be verified then set.
   */
  Employee(String name, String password) {
    if (checkName(name)) {
      this.name = new StringBuilder(name);
      setUsername(name);
      setEmail(name);
    } else {
      this.name = new StringBuilder(name);
      this.username = "default";
      this.email = "user@oracleacademy.Test";
    }
    if (isValidPassword(password)) {
      this.password = password;
    } else {
      this.password = "pw";
    }
  }

  /**
   * Overload toString to produce:
   *
   * <p>Employee Details Name: Tim Lee Username: tlee Email: tim.lee@oracleacademy.Test Initial
   * Password: aBcd!
   *
   * @return Employee toString()
   */
  @Override
  public String toString() {
    return ("\rEmployee Details"
        + "\nName : "
        + name.toString()
        + "\nUsername : "
        + username
        + "\nEmail : "
        + email
        + "\nInitial Password : "
        + password);
  }

  public String secureToString() {
    return ("Name : " + name.toString() + "\nUsername : " + username + "\nEmail : " + email);
  }

  /**
   * Test method to test the Employee class. Not used for GUI.
   *
   * @param args runnable code segment.
   */
  public static void main(String[] args) {
    Employee testEmployee = new Employee("Tim Lee", "Password!");
    System.out.println(testEmployee.toString());
    System.out.println(testEmployee.toString());
  }
}
