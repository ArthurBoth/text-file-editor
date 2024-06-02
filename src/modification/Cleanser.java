package modification;

import io.FileIO;
import constants.FolderPaths;

import java.io.File;

public interface Cleanser {
    public static void clearFile(String fileName, ModifierType regEx) {
        String path = FolderPaths.INPUT_FOLDER + fileName;
        String newPath = FolderPaths.OUTPUT_FOLDER + fileName;
        String text;

        verifyFolders();
        text = FileIO.read(path);

        if (text.isEmpty()) return;
        
        text = regEx.modify(text);

        FileIO.write(newPath, text);
    }

    public static void clearFolder(ModifierType  regEx) {
        File folder = new File(FolderPaths.INPUT_FOLDER);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                clearFile(file.getPath(), regEx);
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

