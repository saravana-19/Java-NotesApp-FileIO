import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Notes App ---");
            System.out.println("1. Add a Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // to skip next line

            switch (choice) {
                case 1:
                    addNote(sc);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 3);

        sc.close();
    }

    private static void addNote(Scanner sc) {
        System.out.print("Enter your note: ");
        String note = sc.nextLine();

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) { // true = append mode
            writer.write(note + "\n");
            System.out.println("✅ Note saved!");
        } catch (IOException e) {
            System.out.println("❌ Error writing note: " + e.getMessage());
        }
    }

    private static void viewNotes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- Your Notes ---");
            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("⚠️ No notes found. Add one first!");
        } catch (IOException e) {
            System.out.println("❌ Error reading notes: " + e.getMessage());
        }
    }
}
