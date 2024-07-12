package constants;

public class StringConstants {
    // For logging
    public static final String ERROR_MSG    = "An error occurred. ";
    public static final String WHEN_READING = "(reading)";
    public static final String WHEN_WRITING = "(writing)";
    public static final String SUCCESS      = "Success! The new file is in";
    public static final String EMPTY_FILE   = "The file is empty.";

    // Utility class 
    public static final String UTILITY_CLASS    = "Utility class";
    
    private StringConstants() {
        throw new IllegalStateException("Utility class");
    }
}
