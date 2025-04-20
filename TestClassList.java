import java.util.*;
import java.io.*;

public class TestClassList {
    private static String line;
    private static String[] commands;
    private static ClassList[] lists;

    /*
     * Opens scanner to read from file
     * If no input, will write message asking for input
     * If invalid input, file doesnt exist, will throw error
     * If input is valid, file exist, will be reading line after line consumming
     * commands
     * Will run method runCommand which will process commands mentioned in file
     */
    public static void main(String[] args) {

        if (args.length > 0) {
            try {
                Scanner file = new Scanner(new File(args[0]));
                int numClass = file.nextInt();
                System.out.println("Creating " + numClass + " class lists");
                lists = new ClassList[numClass];
                for (int i = 0; i < numClass; i++) {
                    lists[i] = new ClassList();
                }
                file.nextLine();
                while (file.hasNextLine()) {
                    line = file.nextLine();
                    commands = line.split(" ");
                    runCommand();
                }

                file.close();
            }

            catch (FileNotFoundException ex) {
                System.out.println("Could not find file.");
            }
        } else {
            System.out.println("Please enter name of file. Can't process with no input");
        }
    }

    /*
     * Decides what to do based on commands read from file
     * Runs method suitable for each command
     */
    public static void runCommand() {
        String action = commands[0];
        if (action.equals("C")) {
            if (commands[1] != null && commands[2] != null) {
                String fileName = commands[1];
                int classNumber = Integer.parseInt(commands[2]);
                System.out.println("Open file named " + fileName + " create ClassList " + classNumber);
                createClassList(fileName, classNumber);
            }
        } else if (action.equals("P")) {
            if (commands[1] != null) {
                int printClassNum = Integer.parseInt(commands[1]);
                System.out.println("Print the contents of ClassList " + printClassNum);
                testPrintClass(printClassNum);
                System.out.println("-------------------------------------------------------------------------");
            }

        } else if (action.equals("D")) {
            if (commands[1] != null && commands[2] != null) {
                int copyTo = Integer.parseInt(commands[1]);
                int copyFrom = Integer.parseInt(commands[2]);
                System.out.println(
                        "Create deep copy of ClassList " + copyTo + " and store result in ClassList  " + copyFrom);
                testDuplicateClass(copyTo, copyFrom);
            }

        } else if (action.equals("E")) {
            if (commands[1] != null && commands[2] != null) {
                int enrollTo = Integer.parseInt(commands[1]);
                int enrollFrom = Integer.parseInt(commands[2]);
                System.out.println(
                        "Enroll all of the students from ClassList " + enrollFrom + " in ClassList " + enrollTo);
                testEnrollAll(enrollTo, enrollFrom);
            }

        } else if (action.equals("U")) {
            if (commands[1] != null && commands[2] != null) {
                int unenrollIn = Integer.parseInt(commands[1]);
                int unenrollFrom = Integer.parseInt(commands[2]);
                System.out.println(
                        "Unenroll all of the students in ClassList " + unenrollFrom + " from ClassList" + unenrollIn);
                testUnenrollAll(unenrollIn, unenrollFrom);
            }

        } else if (action.equals("A")) {
            if (commands[1] != null && commands[2] != null && commands[3] != null) {
                int alsoEnrolledFrom = Integer.parseInt(commands[1]);
                int alsoEnrolledIN = Integer.parseInt(commands[2]);
                int saveEnrolled = Integer.parseInt(commands[3]);
                System.out
                        .println("Create ClassList containing students from ClassList " + alsoEnrolledFrom
                                + " who are also enrolled in ClassList " + alsoEnrolledIN
                                + " and store result as ClassList " + saveEnrolled);
                testAlsoEnrolled(alsoEnrolledFrom, alsoEnrolledIN, saveEnrolled);
            }

        } else if (action.equals("N")) {
            if (commands[1] != null && commands[2] != null && commands[3] != null) {
                int notAlsoEnrolledFrom = Integer.parseInt(commands[1]);
                int notAlsoEnrolledIN = Integer.parseInt(commands[2]);
                int saveNotEnrolled = Integer.parseInt(commands[3]);
                System.out.println(
                        "Creating ClassList " + saveNotEnrolled + " for students in ClassList" + notAlsoEnrolledFrom
                                + " but not enrolled in ClassList " + notAlsoEnrolledIN);
                testNotAlsoEnrolled(notAlsoEnrolledFrom, notAlsoEnrolledIN, saveNotEnrolled);
            }
        }

    }

    /*
     * For command "C" it is asked to open a file and create a ClassList with its
     * contents
     * This function open Scanner to read fie
     * If file doesnt exist, throws exception
     * If file exist, reads each line, separates contents by commas, and creates new
     * student from them
     * Assuming each line has information for one student
     * Enrolls student to newList
     */
    public static void createClassList(String fileName, int count) {
        try {
            Scanner file = new Scanner(new File(fileName));
            ClassList newList = lists[count];
            while (file.hasNextLine()) {
                String line = file.nextLine();
                String data[] = line.split(",");
                if (data.length == 4) {
                    String userName = data[0];
                    int userId = Integer.parseInt(data[1]);
                    String firstName = data[2];
                    String lastName = data[3];
                    Student newStudent = new Student(userName, userId, firstName, lastName);
                    newList.enroll(newStudent);
                }
            }
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("No such file");
        }
    }

    /*
     * Prints ClassList of given number
     */
    public static void testPrintClass(int count) {
        lists[count].print();
    }

    /*
     * Does a deep copy of classList of number source, to classList of number
     * destination
     */
    public static void testDuplicateClass(int source, int destination) {
        lists[destination] = lists[source].duplicate();
    }

    /*
     * Enrolls all students from classList source to classList destination
     */
    public static void testEnrollAll(int destination, int source) {
        lists[destination].enrollAll(lists[source]);
    }

    /*
     * Unenrolls all students from classList source in classList destination
     */
    public static void testUnenrollAll(int destination, int source) {
        lists[destination].unenrollAll(lists[source]);
    }

    /*
     * Saves all students who are enrolled in source and destination, into classList
     * of given number
     */
    public static void testAlsoEnrolled(int destination, int source, int save) {
        lists[save] = lists[source].alsoEnrolledIn(lists[destination]);
    }

    /*
     * Saves all students who are not also enrolled in source from destination, into
     * classList
     * of given number
     */
    public static void testNotAlsoEnrolled(int destination, int source, int save) {
        lists[save] = lists[destination].notAlsoEnrolledIn(lists[source]);
    }
}
