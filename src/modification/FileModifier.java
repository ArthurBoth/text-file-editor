package modification;

import io.FileIO;
import constants.ConfigConstants;

import java.io.File;

public interface FileModifier {
    public static void modifyContentOfFile(String fileName, ModifierType regEx) {
        if (fileName.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file

        String path = ConfigConstants.INPUT_FOLDER + fileName;
        String newPath = ConfigConstants.OUTPUT_FOLDER + ModifierType.REPLACE_EXTENSION.modify(fileName);
        String text;

        FileIO.verifyFolders(ConfigConstants.INPUT_FOLDER, ConfigConstants.OUTPUT_FOLDER);
        text = FileIO.read(path);

        if(text == null) return;

        text = regEx.modify(text);

        FileIO.write(newPath, text);
    }

    public static void modifyContentOfAllFiles(ModifierType  regEx) {
        File folder = new File(ConfigConstants.INPUT_FOLDER);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                modifyContentOfFile(file.getName(), regEx);
            }
        }
    }

    public static void renameFile(String fileName, ModifierType type) {
        if (fileName.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file
        
        FileIO.verifyFolders(ConfigConstants.INPUT_FOLDER, ConfigConstants.OUTPUT_FOLDER);
        String text = FileIO.read(ConfigConstants.INPUT_FOLDER + fileName);
        String newPath = ConfigConstants.OUTPUT_FOLDER + type.modify(fileName);

        if (text == null) return;

        FileIO.write(newPath, text);
    }

    public static void renameFile(String oldFileName, String newFileName) {
        FileIO.verifyFolders(ConfigConstants.INPUT_FOLDER, ConfigConstants.OUTPUT_FOLDER);
        String text = FileIO.read(ConfigConstants.INPUT_FOLDER + oldFileName);
        String newPath = ConfigConstants.OUTPUT_FOLDER + newFileName;

        if (text == null) return;

        FileIO.write(newPath, text);
    }
    

    public static void renameAllFiles(ModifierType type) {
        File folder = new File(ConfigConstants.INPUT_FOLDER);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                renameFile(file.getName(), type);
            }
        }
    }
}
