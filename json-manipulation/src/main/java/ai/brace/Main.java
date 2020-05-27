package ai.brace;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello, world.");

        final JsonLoader jsonLoader = new JsonLoader();
        final TextDataSorter textDataSorter = new TextDataSorter();
        final Counter counter = new Counter();

        // Task 1
        final Map<Integer, String> a1TextMap = jsonLoader.getIdToTextMap("a1.json");
        final List<String> sortedA1Text = textDataSorter.getTextSortedById(a1TextMap);

        System.out.println("\nTask 1\n");
        sortedA1Text.forEach(System.out::println);

        // Task 2
        final Map<Integer, String> a2TextMap = jsonLoader.getIdToTextMap("a2.json");
        a2TextMap.putAll(a1TextMap);
        final List<String> sortedA2Text = textDataSorter.getTextSortedById(a2TextMap);

        System.out.println("\nTask 2\n");
        sortedA2Text.forEach(System.out::println);

        System.out.println("\nTask 3\n");
        System.out.println("Frequency of words from Task 2");
        final Map<String, Integer> frequency = counter.countCaseInsensitiveWordFrequency(sortedA2Text);
        frequency.entrySet().forEach(System.out::println);
    }
}
