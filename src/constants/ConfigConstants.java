package constants;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ConfigConstants {
    // Files configurations
    public static final String RESULT_EXTENSION         = ".csv";
    public static final String GIT_KEEP                 = ".gitkeep";
    public static final String INPUT_FOLDER             = "./.Input/";
    public static final String OUTPUT_FOLDER            = "./.Output/";
    public static final String OUTPUT_FILE              = String.format("_Output File_%s", RESULT_EXTENSION);
    public static final int DEFAULT_PARTITION_SIZE_MB   = 250;
    public static final Charset DEFAULT_CHARSET         = StandardCharsets.UTF_8;

    // Code configurations
    public static final boolean DEFAULT_MEMORYLESS_OPERATION_MODIFY_CONTENT     = false;
    public static final boolean DEFAULT_MEMORYLESS_OPERATION_COMPARE_FILES      = true;
    
    public static final boolean DEFAULT_IGNORE_DATE_TIME_WHEN_COMPARING_FILES   = true;

    // Logging configurations
    public static final boolean PRINT_LOGS = true;

    private ConfigConstants() {
        throw new IllegalStateException(StringConstants.UTILITY_CLASS);
    }
}
