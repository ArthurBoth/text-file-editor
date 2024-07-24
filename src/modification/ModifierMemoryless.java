package modification;

import constants.ConfigConstants;
import constants.StringConstants;
import io.ConsoleLogger;
import io.FileIO;

public class ModifierMemoryless extends Modifier {
    public ModifierMemoryless() {}

    @Override
    public void modifyContentOfFile(String fileName, ModifierType type) {
        if (fileName.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file // TODO Move this check to the ModifierManager

        String path = ConfigConstants.INPUT_FOLDER + fileName;
        String newPath = ConfigConstants.OUTPUT_FOLDER + ModifierType.REPLACE_EXTENSION.modify(fileName);

        readWrite(path, newPath, type);
    }

    @Override
    public void renameFile(String fileName, ModifierType type) {
        if (fileName.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file // TODO Move this check to the ModifierManager
        FileIO.verifyFolders(ConfigConstants.INPUT_FOLDER, ConfigConstants.OUTPUT_FOLDER); // TODO Move this check to the ModifierManager

        String inputPath = ConfigConstants.INPUT_FOLDER + fileName;
        String outputPath = ConfigConstants.OUTPUT_FOLDER + type.modify(fileName);

        copyFile(inputPath, outputPath);
    }

    @Override
    public void renameFile(String oldFileName, String newFileName) {
        if (oldFileName.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file // TODO Move this check to the ModifierManager
        FileIO.verifyFolders(ConfigConstants.INPUT_FOLDER, ConfigConstants.OUTPUT_FOLDER); // TODO Move this check to the ModifierManager

        String inputPath = ConfigConstants.INPUT_FOLDER + oldFileName;
        String outputPath = ConfigConstants.OUTPUT_FOLDER + newFileName;
        
        copyFile(inputPath, outputPath);
    }

    private void readWrite(String inputPath, String outputPath, ModifierType type) {
        FileIO.verifyFolders(ConfigConstants.INPUT_FOLDER, ConfigConstants.OUTPUT_FOLDER);

        int index = 0;
        String line = FileIO.readLine(inputPath, index++);

        if (line == null) {
            ConsoleLogger.log(StringConstants.EMPTY_FILE);
            return;
        }
        
        while (line != null) {
            line = FileIO.readLine(inputPath, index++);
            line = type.modify(line);
            FileIO.writeLine(outputPath, line);
        }
        ConsoleLogger.log(String.format("%s {%s}", StringConstants.SUCCESS, outputPath));
    }

    private void copyFile(String inputPath, String outputPath) {
        FileIO.verifyFolders(ConfigConstants.INPUT_FOLDER, ConfigConstants.OUTPUT_FOLDER);

        int index = 0;
        String line = FileIO.readLine(inputPath, index++);
        
        while (line != null) {
            line = FileIO.readLine(inputPath, index++);
            FileIO.writeLine(outputPath, line);
        }
        ConsoleLogger.log(String.format("%s {%s}", StringConstants.SUCCESS, outputPath));
    }
}
