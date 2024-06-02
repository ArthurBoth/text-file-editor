import io.IO;
import io.PathConstants;

import java.io.File;

public interface Cleanser {
    public static void clearFile(String fileName, String regEx) {
        String path = PathConstants.INPUT_FOLDER_PATH + fileName;
        String newPath = PathConstants.OUTPUT_FOLDER_PATH + fileName;
        String text;

        verifyFolders();
        text = IO.read(path);

        if (text.isEmpty()) return;
        
        text = text.replaceAll(regEx, "");

        if (IO.write(newPath, text)) {
            System.out.println("Success! The new file is in " + newPath);
        }
    }

    public static void clearFolder(String regEx) {
        File folder = new File(PathConstants.INPUT_FOLDER_PATH);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                clearFile(file.getPath(), regEx);
            }
        }
    }

    private static void verifyFolders() {
        File inputFolder = new File(PathConstants.INPUT_FOLDER_PATH);
        File outputFolder = new File(PathConstants.OUTPUT_FOLDER_PATH);

        if (!inputFolder.exists()) {
            inputFolder.mkdir();
        }

        if (!outputFolder.exists()) {
            outputFolder.mkdir();
        }
    }
}
