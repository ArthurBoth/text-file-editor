package modification;

import constants.StringConstants;
import constants.ConfigConstants;

import java.io.File;

public abstract class FileModifier {
    private static Modifier memory = new ModifierMemory();
    private static Modifier memoryless = new ModifierMemoryless();

    public static void modifyContentOfFile(String fileName, ModifierType type, boolean memorylessOperation) {
        if (memorylessOperation) {
            memoryless.modifyContentOfFile(fileName, type);
        } else {
            memory.modifyContentOfFile(fileName, type);
        }
    }

    public static void modifyContentOfFile(String fileName, ModifierType type) {
        modifyContentOfFile(fileName, type, false);
    }

    public static void modifyContentOfAllFiles(ModifierType  type) {
        File folder = new File(ConfigConstants.INPUT_FOLDER);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                modifyContentOfFile(file.getName(), type, false);
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
        if (memorylessOperation) {
            memoryless.renameFile(oldName, newName);
        } else {
            memory.renameFile(oldName, newName);
        }
    }
    public static void renameFile(String oldName, String newName) {
        renameFile(oldName, newName, false);
    }

    public static void renameFile(String fileName, ModifierType type, boolean memorylessOperation) {
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
                renameFile(file.getName(), type, false);
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

    private FileModifier() {
        throw new IllegalStateException(StringConstants.UTILITY_CLASS);
    }
}
