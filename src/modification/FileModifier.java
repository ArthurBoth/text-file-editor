package modification;

import io.IO;
import constants.FolderPaths;

import java.io.File;

public interface FileModifier {
    public static void modifyContentOfFile(String fileName, ModifierType regEx) {
        String path = FolderPaths.INPUT_FOLDER + fileName;
        String newPath = FolderPaths.OUTPUT_FOLDER + ModifierType.REPLACE_EXTENSION.modify(fileName);
        String text;

        verifyFolders();
        text = IO.read(path);
        text = regEx.modify(text);

        if (IO.write(newPath, text)) {
            System.out.println("Success! The new file is in " + newPath);
        }

        if (text.isEmpty()) {
            System.out.println("The file is empty!");
        }
    }

    public static void modifyContentOfAllFiles(ModifierType  regEx) {
        File folder = new File(FolderPaths.INPUT_FOLDER);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                modifyContentOfFile(file.getPath(), regEx);
            }
        }
    }

    public static void renameFile(String fileName, ModifierType type) {
        verifyFolders();
        String text = IO.read(FolderPaths.INPUT_FOLDER + fileName);
        String newPath = FolderPaths.OUTPUT_FOLDER + type.modify(fileName);

        if (IO.write(newPath, text)) {
            System.out.println("Success! The new file is in " + newPath);
        }
    }

    public static void renameAllFiles(ModifierType type) {
        File folder = new File(FolderPaths.INPUT_FOLDER);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                renameFile(file.getName(), type);
            }
        }
    }

    private static void verifyFolders() {
        File inputFolder = new File(FolderPaths.INPUT_FOLDER);
        File outputFolder = new File(FolderPaths.OUTPUT_FOLDER);

        if (!inputFolder.exists()) {
            inputFolder.mkdir();
        }

        if (!outputFolder.exists()) {
            outputFolder.mkdir();
        }
    }
}
