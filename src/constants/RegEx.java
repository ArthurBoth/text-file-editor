package constants;

import java.util.Set;

public class RegEx {
    
    public static final String NEW_LINE = System.lineSeparator();
    public static final String MULTIPLE_EMPTY_LINES = "(\r?\n){2,}";
    public static final String SINGLE_NUMBER = "^\\d+$";
    public static final String TIMESTAMP = "^\\d{2}:\\d{2}:\\d{2},\\d{3} --> \\d{2}:\\d{2}:\\d{2},\\d{3}$";
    public static final String REGEX_BOUNDARY = "\\b";

    public static final Set<String> BAD_WORDS = Set.of(
        "fuck", "fucking", "fucked");
    
    private RegEx() {
        throw new IllegalStateException("Utility class");
    }
}
