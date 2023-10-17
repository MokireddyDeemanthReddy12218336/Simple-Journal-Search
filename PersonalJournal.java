import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class JournalEntry {
    private String date;
    private String content;

    public JournalEntry(String date, String content) {
        this.date = date;
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public List<String> getWordsInContent() {
        return Arrays.asList(content.split("\\s+"));
    }
}

public class PersonalJournal {
    private ArrayList<JournalEntry> entries = new ArrayList<>();

    public void writeEntry(String content) {
        String date = getCurrentDate();
        JournalEntry entry = new JournalEntry(date, content);
        entries.add(entry);
        System.out.println("Entry added successfully!");
    }

    public void searchEntries(String keyword) {
        int entryNumber = 1;
        System.out.println("\nMatching Entries:");
        for (JournalEntry entry : entries) {
            List<String> wordsInContent = entry.getWordsInContent();
            if (wordsInContent.contains(keyword)) {
                System.out.println(entryNumber + ". Date: " + entry.getDate() + ", Content: " + entry.getContent());
                entryNumber++;
            }
        }
        if (entryNumber == 1) {
            System.out.println("No matching entries found.");
        }
    }

    public void displayAllEntries() {
        if (entries.isEmpty()) {
            System.out.println("No entries to display.");
        } else {
            System.out.println("\nAll Entries:");
            for (int i = 0; i < entries.size(); i++) {
                JournalEntry entry = entries.get(i);
                System.out.println((i + 1) + ". Date: " + entry.getDate() + ", Content: " + entry.getContent());
            }
        }
    }

    public void deleteEntry(String specificWord) {
        for (int i = entries.size() - 1; i >= 0; i--) {
            JournalEntry entry = entries.get(i);
            List<String> wordsInContent = entry.getWordsInContent();
            if (wordsInContent.contains(specificWord)) {
                entries.remove(i);
                System.out.println("Entry deleted successfully!");
            }
            else{
                System.out.println("There is no keyword matching from the present entries");
            }
        }
    }

    public String getCurrentDate() {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }

    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nPersonal Journal Menu:");
            System.out.println("1. Write a new entry");
            System.out.println("2. Search for entries");
            System.out.println("3. Display all entries");
            System.out.println("4. Delete an entry by specific word");
            System.out.println("5. Exit");

            System.out.print("Enter your choice : ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Write your journal entry: ");
                    String content = scanner.nextLine();
                    writeEntry(content);
                    break;
                case "2":
                    System.out.print("Enter a specific word to search for: ");
                    String keyword = scanner.nextLine();
                    searchEntries(keyword);
                    break;
                case "3":
                    displayAllEntries();
                    break;
                case "4":
                    System.out.print("Enter a specific word to delete entries: ");
                    String specificWord = scanner.nextLine();
                    deleteEntry(specificWord);
                    break;
                case "5":
                    System.out.println("Exiting the journal application.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        PersonalJournal journal = new PersonalJournal();
        journal.mainMenu();
    }
}
