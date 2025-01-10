package constants;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import io.PartitionUnit;

public class ConfigConstants {
    // Files configurations
    public static final String RESULT_EXTENSION = ".txt";
    public static final String GIT_KEEP         = ".gitkeep";
    public static final String INPUT_FOLDER     = "./.Input/";
    public static final String OUTPUT_FOLDER    = "./.Output/";
    public static final String OUTPUT_FILE      = String.format("_Output File_%s", RESULT_EXTENSION);

    // Code configurations
    public static final boolean DEFAULT_MEMORYLESS_OPERATION_MODIFY_CONTENT   = false;
    public static final boolean DEFAULT_MEMORYLESS_OPERATION_COMPARE_FILES    = true;
    
    public static final boolean DEFAULT_IGNORE_DATE_TIME_WHEN_COMPARING_FILES = true;
    
    // Partition configurations
    public static final int DEFAULT_PARTITION_SIZE           = 250;
    public static final Charset DEFAULT_CHARSET              = StandardCharsets.UTF_8;
    public static final PartitionUnit DEFAULT_PARTITION_UNIT = PartitionUnit.MB;

    // Logging configurations
    public static final boolean PRINT_LOGS = true;

    private ConfigConstants() {
        throw new IllegalStateException(StringConstants.UTILITY_CLASS);
    }
}
