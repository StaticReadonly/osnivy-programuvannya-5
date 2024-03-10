import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class WordFrequency {

    public static void main(String[] args) {
        try {
            String filename = "prog.txt";
            String[] rarestWords = rarestWords(filename);
            System.out.println("Rarest word(s):");
            for (String word : rarestWords) {
                System.out.println(word);
            }
        } catch (IllegalArgumentException | NullPointerException | IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static String[] rarestWords(String filename) throws IOException {
        if (filename == null) {
            throw new NullPointerException("Filename cannot be null");
        }

        Map<String, Integer> wordFrequency = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.trim().split("\\s+");

                for (String word : words) {
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found: " + filename);
        } catch (IOException e) {
            throw new IOException("Error reading file: " + e.getMessage());
        }

        int minFrequency = Integer.MAX_VALUE;
        for (int frequency : wordFrequency.values()) {
            if (frequency < minFrequency) {
                minFrequency = frequency;
            }
        }

        int count = 0;
        for (int frequency : wordFrequency.values()) {
            if (frequency == minFrequency) {
                count++;
            }
        }

        String[] rarestWords = new String[count];
        int index = 0;
        for (Entry<String, Integer> entry : wordFrequency.entrySet()) {
            if (entry.getValue() == minFrequency) {
                rarestWords[index++] = entry.getKey();
            }
        }

        return rarestWords;
    }
}
