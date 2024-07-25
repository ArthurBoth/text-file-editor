package constants;

public class StringConstants {
    // For logging
    public static final String ERROR_MSG                        = "An error occurred. ";
    public static final String WHEN_READING                     = "(reading)";
    public static final String WHEN_WRITING                     = "(writing)";
    public static final String SUCCESS                          = "Success! The new file is in";
    public static final String EMPTY_FILE                       = "The file is empty.";
    public static final String DIFFERENCE_FOUND                 = "Files are different";
    public static final String DIFFERENCE_FOUND_LINE            = "Found a difference in line";
    public static final String DIFFERENCE_FOUND__OUTPUT_FILE    = "A file detailing the differences was created in";
    public static final String NO_DIFFERENCE_FOUND              = "No difference was found between both files";

    // Auxiliaries
    public static final String UTILITY_CLASS    = "Utility class";

    public static String FILE_DIFFERENCE(int number) {
        return String.format("[Difference %d]", number);
    }
    
    private StringConstants() {
        throw new IllegalStateException(UTILITY_CLASS);
    }
}
