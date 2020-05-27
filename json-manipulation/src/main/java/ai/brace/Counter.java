package ai.brace;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Counter {
    public Map<String, Integer> countCaseInsensitiveWordFrequency(final List<String> textArray) {
        final Map<String, Integer> frequency = new TreeMap<>();
        textArray.forEach(text -> Arrays.stream(text.split(" "))
                .forEach(word -> {
                    word = word.toLowerCase().replaceAll("[,|.]", "");
                    final Integer count = frequency.getOrDefault(word, 0);
                    frequency.put(word, count + 1);
                }));
        return frequency;
    }
}
