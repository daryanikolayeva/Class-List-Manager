public class Student implements Comparable<Student> {
    private String username;
    private int id;
    private String firstName;
    private String lastName;

    /*
     * Constructor for class Student
     * Takes username, id, first and last name as parameters
     * Assings them to instant variables of class
     */
    public Student(String username, int id, String firstName, String lastName) {
        this.username = username;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /*
     * Get students's userName
     */
    public String getUserName() {
        return username;
    }

    /*
     * Get students's Id
     */
    public int getId() {
        return id;
    }

    /*
     * Get students's first Name
     */
    public String getfirstName() {
        return firstName;
    }

    /*
     * Get students's last name
     */
    public String getlasttName() {
        return lastName;
    }

    /*
     * Compares usernames of current student and other
     * if they're identical gives 0
     * if at first mismatch value of char in current student > at other
     * student, then gives positve
     * Else gives negative
     */
    public int compareTo(Student other) {
        return this.username.compareTo(other.username);
    }

}