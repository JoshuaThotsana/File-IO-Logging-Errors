import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.util.Scanner;

/**
 * A back-end-service that will help capture basic information about prospective students who
 * come to inquire here at Umuzi.
 * @author Joshua Thotsana Mabotsa.
 */

public class Visitor {

    String fullName;
    int age;
    String date;
    String time;
    String comments;
    String assistant;

    private static final Logger logger = LogManager.getLogger(Visitor.class.getName());

    /**
     * Construct a new Visitor instance.
     * @param fullName      name and surname of the student
     * @param age           age of the student
     * @param date          date of visit
     * @param time          time of visit
     * @param comments      general comments upon visit
     * @param assistant     name of the person who assisted the visitor
     */
    public Visitor(String fullName, int age, String date, String time, String comments, String assistant) {
        this.fullName = fullName;
        this.age = age;
        this.date = date;
        this.time = time;
        this.comments = comments;
        this.assistant = assistant;
    }

    /**
     * Saves visitor's data to the a text file.
     * @return      return a message stating whether a new text file was created or it already exist.
     */
    public String save() {

        String fileName = "visitor_"+fullName.split(" ")[0].toLowerCase() +"_" +
                fullName.split(" ")[1].toLowerCase()+".txt";
        String results = "File " + fileName+ " was created.";

        try {
            File file = new File(fileName);
            if (file.exists()) {
                results = "File already exists.";
            }
            FileWriter fileWriter = new FileWriter(file,true);
            fileWriter.write("Name: " + fullName.split(" ")[0] + "\n"
                                + "Surname: " + fullName.split(" ")[1] + "\n"
                                + "Age: " + age + "\n"
                                + "Date: " + date + "\n"
                                + "Time: " + time + "\n"
                                + "Comments: " + comments + "\n"
                                + "Assistant: " + assistant + "\n"
                                + "................................\n\n");
            fileWriter.close();
        } catch (Exception e) {
            logger.error("An error occurred.");
        }
        return results;
    }

    /**
     * Prints the contents of the text file to the console.
     * @param fullName the name of the visitor.
     * @return returns the contents of the text file.
     */
    public String load(String fullName) {

        String fileName = "visitor_"+fullName.split(" ")[0].toLowerCase() +"_" +
                fullName.split(" ")[1].toLowerCase()+".txt";

        StringBuilder results = new StringBuilder();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                results.append(scanner.nextLine()).append("\n");
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
        return "\n"+results.toString();
    }

    /**
     * The main method signature
     * @param args parameter for the main method
     */
    public static void main(String[] args) {

        Visitor alice = new Visitor("alice cooper",50,"2020/01/17","09:45",
                "general enquiry","Mesuli"); // instantiate alice object.

        Visitor bob = new Visitor("bob marley",32,"2019/12/28","13:58",
                "none","Sheena"); // instantiate bob object.

        Visitor charlie = new Visitor("charlie sheen",23,"2019/10/23","15:13",
                "no comment","Liezel"); // instantiate charlie object.

        logger.info(alice.save());
        logger.info(bob.save());
        logger.info(charlie.save());

        logger.info(alice.load("Alice Cooper"));
        logger.info(bob.load("Bob Marley"));
        logger.info(charlie.load("Charlie Sheen"));
    }
}
