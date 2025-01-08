package constants;

public class StringConstants {
    // For logging
    public static final String ERROR_MSG                        = "An error occurred. ";
    public static final String WHEN_READING                     = "(reading)";
    public static final String WHEN_WRITING                     = "(writing)";
    public static final String WHEN_PARTITIONING                = "(partitioning)";
    public static final String SUCCESS                          = "Success! The new file is in";
    public static final String EMPTY_FILE                       = "The file is empty.";
    public static final String DIFFERENCE_FOUND                 = "Files are different";
    public static final String DIFFERENCE_FOUND_LINE            = "Found a difference in line";
    public static final String DIFFERENCE_FOUND__OUTPUT_FILE    = "A file detailing the differences was created in";
    public static final String NO_DIFFERENCE_FOUND              = "No difference was found between both files";

    // Auxiliaries
    public static final String UTILITY_CLASS    = "Utility class";

    public static String FILE_DIFFERENCE(int index, int line) {
        return String.format("[Difference %d in line %d]%n", index, line);
    }

    public static String PARTITION_FORMATTER(long fileSize, long partitionSize) {
        long numberOfChunks = (fileSize / partitionSize) + 1;
        int digits          = String.valueOf(numberOfChunks).toCharArray().length;
        return String.format("%%s%%s-%%0%dd%%s", digits); // 5 -> %s%s-%05d%s
    }
    
    private StringConstants() {
        throw new IllegalStateException(UTILITY_CLASS);
    }
}
