package modification;

import io.ConsoleLogger;
import io.FileIO;
import constants.ConfigConstants;
import constants.StringConstants;

public class ModifierMemory extends Modifier{
    public ModifierMemory() {}

    @Override
    public void modifyContentOfFile(String fileName, ModifierType type) {
        if (fileName.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file // TODO Move this check to the FileModifier
        FileIO.verifyFolders(ConfigConstants.INPUT_FOLDER, ConfigConstants.OUTPUT_FOLDER); // TODO Move this check to the FileModifier

        String path = ConfigConstants.INPUT_FOLDER + fileName;
        String newPath = ConfigConstants.OUTPUT_FOLDER + ModifierType.REPLACE_EXTENSION.modify(fileName);
        String text;

        text = FileIO.read(path);

        if(text == null) return;

        text = type.modify(text);

        FileIO.write(newPath, text);
    }

    @Override
    public void renameFile(String fileName, ModifierType type) {
        if (fileName.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file // TODO Move this check to the FileModifier
        FileIO.verifyFolders(ConfigConstants.INPUT_FOLDER, ConfigConstants.OUTPUT_FOLDER); // TODO Move this check to the FileModifier

        String text = FileIO.read(ConfigConstants.INPUT_FOLDER + fileName);
        String newPath = ConfigConstants.OUTPUT_FOLDER + type.modify(fileName);

        if (text == null) return;

        FileIO.write(newPath, text);
    }

    @Override
    public void renameFile(String oldFileName, String newFileName) {
        if (oldFileName.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file // TODO Move this check to the FileModifier
        FileIO.verifyFolders(ConfigConstants.INPUT_FOLDER, ConfigConstants.OUTPUT_FOLDER); // TODO Move this check to the FileModifier

        String text = FileIO.read(ConfigConstants.INPUT_FOLDER + oldFileName);
        String newPath = ConfigConstants.OUTPUT_FOLDER + newFileName;

        if (text == null) return;

        FileIO.write(newPath, text);
    }

    @Override
    public void compareFiles(String file1, String file2, boolean ignoreDateTime) {
        if (file1.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file // TODO Move this check to the FileModifier
        if (file2.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file // TODO Move this check to the FileModifier
        FileIO.verifyFolders(ConfigConstants.INPUT_FOLDER, ConfigConstants.OUTPUT_FOLDER); // TODO Move this check to the FileModifier

        String text1 = FileIO.read(ConfigConstants.INPUT_FOLDER + file1);
        String text2 = FileIO.read(ConfigConstants.INPUT_FOLDER + file2);

        if (!(text1.equals(text2))) {
            ConsoleLogger.logWhite(StringConstants.DIFFERENCE_FOUND);
        } else {
            ConsoleLogger.logGreen(StringConstants.NO_DIFFERENCE_FOUND);
        }
    }
}
