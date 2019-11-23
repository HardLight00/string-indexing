import java.util.*;
import java.util.stream.Collectors;

public class Indexer {
    private int minCount;
    private String deliminator;

    Indexer(int minCount, String deliminator) {
        this.minCount = minCount;
        this.deliminator = deliminator;
    }

    private Map<Character, Set<String>> stringToMap(String str) {
        Map<Character, Set<String>> map = new TreeMap<>();

        String[] words = str.split(this.deliminator);
        char startChar;
        for (String word : words) {
            startChar = word.charAt(0);
            map.computeIfAbsent(startChar, x -> new TreeSet<>(Comparator.comparingInt(String::length).reversed()
                    .thenComparing(o -> o)));
            map.get(startChar).add(word);
        }

        return map.entrySet().stream()
                .filter(entry -> entry.getValue().size() >= this.minCount)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (v1, v2) -> {
                            throw new RuntimeException(String.format("Duplicate key for values %s and %s", v1, v2));
                        },
                        TreeMap::new));
    }

    private String makeIndexing(String input) {
        Map<Character, Set<String>> inputMap = stringToMap(input);
        Iterator keysIterator = inputMap.keySet().iterator();

        StringBuilder result = new StringBuilder("[");
        while (keysIterator.hasNext()) {
            Character key = (Character) keysIterator.next();
            result.append(key)
                    .append("=")
                    .append(inputMap.get(key).toString())
                    .append(keysIterator.hasNext() ? ", " : "]");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Indexer indexer = new Indexer(2, " ");
        System.out.println(indexer.makeIndexing("сапог сарай арбуз болт бокс биржа"));
        System.out.println(indexer.makeIndexing("care apple car break bread boost"));
    }
}
