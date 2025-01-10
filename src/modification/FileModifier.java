package modification;

import constants.StringConstants;
import io.PartitionUnit;
import constants.ConfigConstants;

import java.io.File;

public abstract class FileModifier {
    private static Modifier memory     = new ModifierMemory();
    private static Modifier memoryless = new ModifierMemoryless();

    public static void modifyContentOfFile(String fileName, ModifierType type, boolean memorylessOperation) {
        if (fileName.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file
        verifyFolders();
        
        if (memorylessOperation) {
            memoryless.modifyContentOfFile(fileName, type);
        } else {
            memory.modifyContentOfFile(fileName, type);
        }
    }

    public static void modifyContentOfFile(String fileName, ModifierType type) {
        modifyContentOfFile(fileName, type, ConfigConstants.DEFAULT_MEMORYLESS_OPERATION_MODIFY_CONTENT);
    }

    public static void modifyContentOfAllFiles(ModifierType  type) {
        File folder  = new File(ConfigConstants.INPUT_FOLDER);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                modifyContentOfFile(file.getName(), type, ConfigConstants.DEFAULT_MEMORYLESS_OPERATION_MODIFY_CONTENT);
            }
        }
    }
    public static void modifyContentOfAllFiles(ModifierType  type, boolean memorylessOperations) {
        File folder  = new File(ConfigConstants.INPUT_FOLDER);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                modifyContentOfFile(file.getName(), type, memorylessOperations);
            }
        }
    }

    public static void renameFile(String oldName, String newName) {
        if (oldName.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file
        if (newName.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file
        verifyFolders();

        File oldFile = new File(ConfigConstants.INPUT_FOLDER + oldName);
        File newFile = new File(ConfigConstants.OUTPUT_FOLDER + newName);

        oldFile.renameTo(newFile);
    }

    public static void renameFile(String fileName, ModifierType type) {
        if (fileName.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file
        verifyFolders();

        File oldFile = new File(ConfigConstants.INPUT_FOLDER + fileName);
        File newFile = new File(ConfigConstants.OUTPUT_FOLDER + type.modify(fileName));

        oldFile.renameTo(newFile);
    }

    public static void renameAllFiles(ModifierType type) {
        File folder  = new File(ConfigConstants.INPUT_FOLDER);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                renameFile(file.getName(), type);
            }
        }
    }

    public static void compareFiles(String file1, String file2, boolean memorylessOperation, boolean ignoreDateTime) {
        if (file1.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file
        if (file2.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file
        verifyFolders();

        if (memorylessOperation) {
            memoryless.compareFiles(file1, file2, ignoreDateTime); 
        } else {
            memory.compareFiles(file1, file2, ignoreDateTime);
        }
    }

    public static void compareFiles(String file1, String file2, boolean memorylessOperation) {
        compareFiles(file1, file2, memorylessOperation, ConfigConstants.DEFAULT_IGNORE_DATE_TIME_WHEN_COMPARING_FILES);
    }

    public static void compareFiles(String file1, String file2) {
        compareFiles(file1, file2, ConfigConstants.DEFAULT_MEMORYLESS_OPERATION_COMPARE_FILES);
    }

    private static void verifyFolders() {
        File inputFolder  = new File(ConfigConstants.INPUT_FOLDER);
        File outputFolder = new File(ConfigConstants.OUTPUT_FOLDER);

        if (!inputFolder.exists()) {
            inputFolder.mkdir();
        }

        if (!outputFolder.exists()) {
            outputFolder.mkdir();
        }
    }

    public static void partitionFile(String fileName) {
        partitionFile(fileName, ConfigConstants.DEFAULT_PARTITION_SIZE, ConfigConstants.DEFAULT_PARTITION_UNIT);
    }

    public static void partitionFile(String fileName, int partitionSize) {
        partitionFile(fileName, partitionSize, ConfigConstants.DEFAULT_PARTITION_UNIT);
    }

    public static void partitionFile(String fileName, int partitionSize, PartitionUnit unit) {
        File file        = new File(ConfigConstants.INPUT_FOLDER + fileName);
        String formatter = StringConstants.PARTITION_FORMATTER(file.length(), unit.getBytes(partitionSize));
        Modifier.partitionFile(fileName, ConfigConstants.OUTPUT_FOLDER, partitionSize, unit, formatter);
    }

     private FileModifier() {
        throw new IllegalStateException(StringConstants.UTILITY_CLASS);
    }
}
