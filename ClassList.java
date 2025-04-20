
public class ClassList {
    private Node array[] = new Node[1009];

    /*
     * Takes username as input parameter, makes int key/hash of it by Horner's
     * method
     */
    int hashFunction(String userName) {
        final int A = 11;
        int result = 0;
        int length = 1021;
        int m = userName.length();
        for (int i = 0; i < m; i++) {
            result = (A * result + userName.charAt(i)) % length;
        }
        return result;
    }

    /*
     * Takes student of type Student as input parameter
     * Checks if this student is already in array of Students by hash that this
     * student has
     */
    public boolean contains(Student student) {
        int key = hashFunction(new Node(student, null).getUserName());
        Node curr = array[key];
        while (curr != null) {
            if (curr.compareTo(student) == 0) {
                return true;
            } else {
                curr = curr.link;
            }
        }
        return false;
    }

    /*
     * Takes student of type Student as input parameter
     * First checks if this student already in ClassList
     * If not, enrolls it
     */
    public void enroll(Student student) {
        if (contains(student)) {
            return;
        }
        int key = hashFunction(new Node(student, null).getUserName());
        Node newNode = new Node(student, array[key]);
        array[key] = newNode;
    }

    /*
     * Takes student of type Student as input parameter
     * First cheks if this student already exist
     * If it does, checks where( top or further )
     * Unenrolls
     */
    public void unenroll(Student student) {
        if (!contains(student)) {
            return;
        }
        int key = hashFunction(new Node(student, null).getUserName());
        Node curr = array[key];
        Node prev = null;
        while (curr != null) {
            if (curr.compareTo(student) == 0) {
                if (prev == null) {
                    array[key] = curr.link;
                } else {
                    prev.link = curr.link;
                }
            }
            prev = curr;
            curr = curr.link;
        }

    }

    /*
     * Takes otherList as a parameter
     * Checks one by one if students from otherList is not enrolled in exisiting
     * list,
     * Enrolls them to existing list
     * Traverse till reach end of otherList
     */
    public void enrollAll(ClassList otherList) {
        for (int i = 0; i < otherList.array.length; i++) {
            Node otherListCurr = otherList.array[i];
            while (otherListCurr != null) {
                if (!this.contains(otherListCurr.getStudentData())) {
                    this.enroll(otherListCurr.getStudentData());
                }
                otherListCurr = otherListCurr.link;
            }
        }
    }

    /*
     * Takes otherList as a parameter
     * Checks one by one if students from otherList enrolled in exisiting list,
     * Unenrolls them from exisiting list
     * Traverse till reach end of otherList
     */
    public void unenrollAll(ClassList otherList) {
        for (int i = 0; i < otherList.array.length; i++) {
            Node otherListCurr = otherList.array[i];
            while (otherListCurr != null) {
                if (this.contains(otherListCurr.getStudentData())) {
                    this.unenroll(otherListCurr.getStudentData());
                }
                otherListCurr = otherListCurr.link;
            }
        }
    }

    /*
     * Creates deep copy of current list
     * Traverse through current list taking every student and creating copy of him
     * Creates new student with parameter of exisiting, enrolls to duplicate list
     * Returns duplicate List
     */
    public ClassList duplicate() {
        ClassList duplicateList = new ClassList();
        for (int i = 0; i < array.length; i++) {
            Node currList = array[i];
            while (currList != null) {
                String copyUserName = currList.getUserName();
                int copyId = currList.getId();
                String copyFirstName = currList.getFirstName();
                String copyLastName = currList.getLasttName();
                Student copyStudent = new Student(copyUserName, copyId, copyFirstName, copyLastName);
                duplicateList.enroll(copyStudent);
                currList = currList.link;
            }
        }
        return duplicateList;
    }

    /*
     * Takes otherList as input parameter
     * Checks students in current list and if they're enrolled in otherList as well
     * Those who are, are added to new ClassList called alsoEnrolled, which is later
     * returned
     */
    public ClassList alsoEnrolledIn(ClassList otherList) {
        ClassList alsoEnrolled = new ClassList();
        for (int i = 0; i < array.length; i++) {
            Node curr = array[i];
            while (curr != null) {
                if (otherList.contains(curr.getStudentData())) {
                    alsoEnrolled.enroll(curr.getStudentData());
                }
                curr = curr.link;
            }
        }
        return alsoEnrolled;
    }

    /*
     * Takes otherList as input parameter
     * Checks students in current list and if they're not also enrolled in otherList
     * Those who are not, are added to new ClassList called notEnrolled, which is
     * later
     * returned
     */
    public ClassList notAlsoEnrolledIn(ClassList otherList) {
        ClassList notEnrolled = new ClassList();
        for (int i = 0; i < array.length; i++) {
            Node curr = array[i];
            while (curr != null) {
                if (!otherList.contains(curr.getStudentData())) {
                    notEnrolled.enroll(curr.getStudentData());
                }
                curr = curr.link;
            }
        }

        return notEnrolled;
    }

    /*
     * Prints student at current ClassList
     */
    public void print() {
        for (int i = 0; i < array.length; i++) {
            Node curr = array[i];
            while (curr != null) {
                String userName = curr.getUserName();
                int Id = curr.getId();
                String firstName = curr.getFirstName();
                String lastName = curr.getLasttName();
                System.out.println("Username: " + userName + ", StudentId: " + Id + ", First Name: " + firstName
                        + ", Last name: " + lastName);
                curr = curr.link;
            }
        }
    }

    ///////////////////////////////////////////////////////
    private class Node {
        public Student studentData;
        public Node link;

        /*
         * Constructor for Node
         * Input parameters are student and link to the next node
         */
        private Node(Student studentData, Node link) {
            this.studentData = studentData;
            this.link = link;
        }

        /*
         * gets student's data for given Node
         */
        public Student getStudentData() {
            return this.studentData;
        }

        /*
         * gets student's username for given Node
         */
        public String getUserName() {
            return this.studentData.getUserName();
        }

        /*
         * gets student's Id for given Node
         */
        public int getId() {
            return this.studentData.getId();
        }

        /*
         * gets student's first name for given Node
         */
        public String getFirstName() {
            return this.studentData.getfirstName();
        }

        /*
         * gets student's last name for given Node
         */
        public String getLasttName() {
            return this.studentData.getlasttName();
        }

        /*
         * compares two nodes by student User Name they hold
         * if they're identical gives 0
         * if at first mismatch value of char in current Node student > at other
         * student, then gives positve
         * Else gives negative
         */
        public int compareTo(Student other) {
            return this.studentData.getUserName().compareTo(other.getUserName());
        }

    }
}
