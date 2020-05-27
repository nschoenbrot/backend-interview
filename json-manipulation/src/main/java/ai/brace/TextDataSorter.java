package ai.brace;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TextDataSorter {

    public List<String> getTextSortedById(final Map<Integer, String> idToText) {
        final Map<String, Integer> mapInverse = idToText.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        return new ArrayList<>(idToText.values()).stream()
                .sorted((text1, text2) -> {
                    final Integer int1 = mapInverse.get(text1);
                    final Integer int2 = mapInverse.get(text2);
                    return int1.compareTo(int2);
                })
                .collect(Collectors.toList());
    }
}
