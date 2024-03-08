import java.io.*;
import java.util.*;

/**
 * Array class provides functionality for managing a knowledge base stored in a file.
 * This class allows users to load a knowledge base from a file, add new statements to the knowledge base,
 * search for items by term or term and sentence, and quit the program.
 */
public class GenericsKbArrayApp {

    /**
     * Checks if a string starts with a given word within an array of strings.
     * 
     * @param a The array of strings to search within.
     * @param b The word to search for.
     * @return true if any string in the array starts with the given word, false otherwise.
     */
    private static boolean foundIn(String[] a, String b) {
        int i = 0;
        boolean result = false;
        while (true) {
            if (a[i] == null) {
                break;
            } else {
                if (a[i].startsWith(b)) {
                    result = true;
                }
            }
            i++;
        }
        return result;
    }

    /**
     * Main method for managing the knowledge base.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int x = 0; // Control variable for while loop
        String filename = ""; // Name of the file to load/save knowledge base
        String searchTerm = ""; // Term to search for in the knowledge base
        String searchStatement = ""; // Statement to search for in the knowledge base
        String bin; // Temporary variable
        Scanner keyboard = new Scanner(System.in); // Scanner object for user input
        File file;
        String[] fileArray = new String[50020]; // Array to store the knowledge base

        // Main loop for program execution
        do {
            // Display menu options
            System.out.print("Choose an action from the menu:\n" +
                    "1. Load a knowledge base from a file\n" +
                    "2. Add a new statement to the knowledge base\n" +
                    "3. Search for an item in the knowledge base by term\n" +
                    "4. Search for an item in the knowledge base by term and sentence\n" +
                    "5. Quit\n" +
                    "Enter your choice: ");
            int choice = keyboard.nextInt(); // User's choice
            keyboard.nextLine(); // Consume newline left-over

            // Switch statement to handle menu choices
            switch (choice) {
                case 1:
                    // Load knowledge base from a file
                    System.out.print("Enter file name: ");
                    filename = keyboard.next();
                    file = new File(filename);
                    while (!file.exists()) {
                        System.out.print("File does not exist, try again.\n");
                        System.out.print("Enter file name: ");
                        filename = keyboard.next();
                        file = new File(filename);
                    }
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(filename));
                        String s;
                        int count = 0;
                        while ((s = reader.readLine()) != null) {
                            fileArray[count++] = s;
                        }
                        reader.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred");
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    // Add a new statement to the knowledge base
                    try {
                        FileWriter newInfo = new FileWriter(filename);
                        System.out.print("Enter the term: ");
                        String term = keyboard.nextLine();
                        System.out.print("Enter the statement: ");
                        String statement = keyboard.nextLine();
                        System.out.print("Enter the confidence score: ");
                        double score = keyboard.nextDouble();
                        while (!((score <= 1) && (score > 0))) {
                            System.out.print("Invalid confidence score (must be between 0 & 1)\n");
                            System.out.print("Enter the confidence score: ");
                            score = keyboard.nextDouble();
                        }
                        file = new File(filename);
                        if (file.delete()) {
                            file.createNewFile();
                        }
                        if (foundIn(fileArray, term)) {
                            for (int j = 0; j < fileArray.length; j++) {
                                if (fileArray[j].startsWith(term)) {
                                    if ((fileArray[j].lastIndexOf("0.") != -1)
                                            && (score > Double.parseDouble(fileArray[j]
                                                    .substring(fileArray[j].lastIndexOf("0."))))) {
                                        fileArray[j] = term + "\t" + statement + "\t" + score;
                                    }
                                    break;
                                }
                            }
                        } else if (!(foundIn(fileArray, term))) {
                            for (int j = 0; j < fileArray.length; j++) {
                                if (fileArray[j] == null) {
                                    fileArray[j] = term + "\t" + statement + "\t" + score;
                                    break;
                                }
                            }
                        }
                        for (String line : fileArray) {
                            if (line == null) {
                                break;
                            }
                            newInfo.write(line + "\n");
                        }
                        newInfo.close();
                        System.out.printf("Statement for term %s has been updated.\n", term);
                    } catch (IOException e) {
                        System.out.println("An error occurred");
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    // Search for an item in the knowledge base by term
                    System.out.print("Enter the term to search: ");
                    searchTerm = keyboard.nextLine();
                    if (fileArray[0] != null) {
                        for (String line : fileArray) {
                            if (line != null) {
                                if ((line.startsWith(searchTerm)) && (line.lastIndexOf("0.") != -1)) {
                                    String result = line.substring(searchTerm.length(), line.lastIndexOf("0."));
                                    result = result.trim();
                                    double confidenceScore = Double.parseDouble(line.substring(line.lastIndexOf("0.")));
                                    System.out.printf("Statement found: %s (Confidence score: %.2f)\n", result,
                                            confidenceScore);
                                } else if ((line.startsWith(searchTerm)) && (line.lastIndexOf("1.") != -1)) {
                                    String result = line.substring(searchTerm.length(), line.lastIndexOf("1."));
                                    result = result.trim();
                                    double confidenceScore = Double.parseDouble(line.substring(line.lastIndexOf("1.")));
                                    System.out.printf("Statement found: %s (Confidence score: %.2f)\n", result,
                                            confidenceScore);
                                }
                            }
                        }
                    } else {
                        System.out.println("Statement not found.");
                    }
                    break;
                case 4:
                    // Search for an item in the knowledge base by term and sentence
                    System.out.print("Enter the term to search: ");
                    searchTerm = keyboard.nextLine();
                    System.out.print("Enter the statement to search for: ");
                    searchStatement = keyboard.nextLine();
                    if (fileArray[0] != null) {
                        for (String line : fileArray) {
                            if (line != null) {
                                if ((line.startsWith(searchTerm + "\t" + searchStatement))
                                        && (line.lastIndexOf("0.") != -1)) {
                                    double confidenceScore = Double.parseDouble(line.substring(line.lastIndexOf("0.")));
                                    System.out.printf("The statement was found and has a confidence score of %.2f.\n",
                                            confidenceScore);
                                } else if ((line.startsWith(searchTerm + "\t" + searchStatement))
                                        && (line.lastIndexOf("1.") != -1)) {
                                    double confidenceScore = Double.parseDouble(line.substring(line.lastIndexOf("1.")));
                                    System.out.printf("The statement was found and has a confidence score of %.2f.\n",
                                            confidenceScore);
                                }
                            }
                        }
                    } else {
                        System.out.println("Statement not found.");
                    }
                    break;
                case 5:
                    // Quit the program
                    x = 5;
                    break;
                default:
                    System.out.println("Invalid options, choose between 1 and 5(inclusive)");
            }
        } while (x != 5);
    }
}
