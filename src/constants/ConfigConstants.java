package constants;

public class ConfigConstants {
    // Files configurations
    public static final String RESULT_EXTENSION = ".csv";
    public static final String GIT_KEEP         = ".gitkeep";
    public static final String INPUT_FOLDER     = "./.Input/";
    public static final String OUTPUT_FOLDER    = "./.Output/";


    // Logging configurations
    public static final boolean PRINT_LOGS = true;

    private ConfigConstants() {
        throw new IllegalStateException("Utility class");
    }
}
