package modification;

import constants.StringConstants;
import constants.ConfigConstants;

import java.io.File;

public abstract class FileModifier {
    private static Modifier memory = new ModifierMemory();
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
        File folder = new File(ConfigConstants.INPUT_FOLDER);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                modifyContentOfFile(file.getName(), type, ConfigConstants.DEFAULT_MEMORYLESS_OPERATION_MODIFY_CONTENT);
            }
        }
    }
    public static void modifyContentOfAllFiles(ModifierType  type, boolean memorylessOperations) {
        File folder = new File(ConfigConstants.INPUT_FOLDER);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                modifyContentOfFile(file.getName(), type, memorylessOperations);
            }
        }
    }

    public static void renameFile(String oldName, String newName, boolean memorylessOperation) {
        if (oldName.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file
        if (newName.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file
        verifyFolders();

        if (memorylessOperation) {
            memoryless.renameFile(oldName, newName);
        } else {
            memory.renameFile(oldName, newName);
        }
    }
    public static void renameFile(String oldName, String newName) {
        renameFile(oldName, newName, ConfigConstants.DEFAULT_MEMORYLESS_OPERATION_RENAME_FILE);
    }

    public static void renameFile(String fileName, ModifierType type, boolean memorylessOperation) {
        if (fileName.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file
        verifyFolders();

        if (memorylessOperation) {
            memoryless.renameFile(fileName, type);
        } else {
            memory.renameFile(fileName, type);
        }
    }

    public static void renameAllFiles(ModifierType type) {
        File folder = new File(ConfigConstants.INPUT_FOLDER);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                renameFile(file.getName(), type, ConfigConstants.DEFAULT_MEMORYLESS_OPERATION_RENAME_FILE);
            }
        }
    }

    public static void renameAllFiles(ModifierType type, boolean memorylessOperations) {
        File folder = new File(ConfigConstants.INPUT_FOLDER);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                renameFile(file.getName(), type, memorylessOperations);
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
        compareFiles(file1, file2, memorylessOperation, ConfigConstants.DEFAULT_MEMORYLESS_OPERATION_COMPARE_FILES);
    }

    public static void compareFiles(String file1, String file2) {
        compareFiles(file1, file2, ConfigConstants.DEFAULT_MEMORYLESS_OPERATION_COMPARE_FILES);
    }

    private static void verifyFolders() {
        File inputFolder = new File(ConfigConstants.INPUT_FOLDER);
        File outputFolder = new File(ConfigConstants.OUTPUT_FOLDER);

        if (!inputFolder.exists()) {
            inputFolder.mkdir();
        }

        if (!outputFolder.exists()) {
            outputFolder.mkdir();
        }
    }

    private FileModifier() {
        throw new IllegalStateException(StringConstants.UTILITY_CLASS);
    }
}
