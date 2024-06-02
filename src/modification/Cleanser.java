package modification;

import io.IO;
import constants.FolderPaths;

import java.io.File;

public interface Cleanser {
    public static void clearFile(String fileName, ModifierType regEx) {
        String path = FolderPaths.INPUT_FOLDER + fileName;
        String newPath = FolderPaths.OUTPUT_FOLDER + fileName;
        String text;

        verifyFolders();
        text = IO.read(path);

        if (text.isEmpty()) return;
        
        text = regEx.modify(text);

        if (IO.write(newPath, text)) {
            System.out.println("Success! The new file is in " + newPath);
        }
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
