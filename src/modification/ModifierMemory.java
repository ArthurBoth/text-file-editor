package modification;

import io.FileIO;
import constants.ConfigConstants;

public class ModifierMemory extends Modifier{
    public ModifierMemory() {}

    @Override
    public void modifyContentOfFile(String fileName, ModifierType type) {
        if (fileName.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file // TODO Move this check to the ModifierManager

        String path = ConfigConstants.INPUT_FOLDER + fileName;
        String newPath = ConfigConstants.OUTPUT_FOLDER + ModifierType.REPLACE_EXTENSION.modify(fileName);
        String text;

        FileIO.verifyFolders(ConfigConstants.INPUT_FOLDER, ConfigConstants.OUTPUT_FOLDER); // TODO Move this check to the ModifierManager
        text = FileIO.read(path);

        if(text == null) return;

        text = type.modify(text);

        FileIO.write(newPath, text);
    }

    @Override
    public void renameFile(String fileName, ModifierType type) {
        if (fileName.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file // TODO Move this check to the ModifierManager
        
        FileIO.verifyFolders(ConfigConstants.INPUT_FOLDER, ConfigConstants.OUTPUT_FOLDER); // TODO Move this check to the ModifierManager
        String text = FileIO.read(ConfigConstants.INPUT_FOLDER + fileName);
        String newPath = ConfigConstants.OUTPUT_FOLDER + type.modify(fileName);

        if (text == null) return;

        FileIO.write(newPath, text);
    }

    @Override
    public void renameFile(String oldFileName, String newFileName) {
        if (oldFileName.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file // TODO Move this check to the ModifierManager

        FileIO.verifyFolders(ConfigConstants.INPUT_FOLDER, ConfigConstants.OUTPUT_FOLDER); // TODO Move this check to the ModifierManager
        String text = FileIO.read(ConfigConstants.INPUT_FOLDER + oldFileName);
        String newPath = ConfigConstants.OUTPUT_FOLDER + newFileName;

        if (text == null) return;

        FileIO.write(newPath, text);
    }
}
