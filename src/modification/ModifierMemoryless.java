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

    private void readWrite(String inputPath, String outputPath, ModifierType type) {
        FileIO.verifyFolders(ConfigConstants.INPUT_FOLDER, ConfigConstants.OUTPUT_FOLDER);

        int index = 0;
        String line = FileIO.readLine(inputPath, index++);

        if (line == null) {
            ConsoleLogger.logWhite(StringConstants.EMPTY_FILE);
            return;
        }
        
        while (line != null) {
            line = FileIO.readLine(inputPath, index++);
            line = type.modify(line);
            FileIO.writeLine(outputPath, line);
        }
        ConsoleLogger.logGreen(String.format("%s {%s}", StringConstants.SUCCESS, outputPath));
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

    private void copyFile(String inputPath, String outputPath) {
        FileIO.verifyFolders(ConfigConstants.INPUT_FOLDER, ConfigConstants.OUTPUT_FOLDER);

        int index = 0;
        String line = FileIO.readLine(inputPath, index++);
        
        while (line != null) {
            line = FileIO.readLine(inputPath, index++);
            FileIO.writeLine(outputPath, line);
        }
        ConsoleLogger.logGreen(String.format("%s {%s}", StringConstants.SUCCESS, outputPath));
    }

    public void compareFiles(String file1, String file2, boolean ignoreDateTime) {
        if (file1.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file // TODO Move this check to the ModifierManager
        if (file2.equals(ConfigConstants.GIT_KEEP)) return; // Skips the '.gitkeep' file // TODO Move this check to the ModifierManager
        FileIO.verifyFolders(ConfigConstants.INPUT_FOLDER, ConfigConstants.OUTPUT_FOLDER); // TODO Move this check to the ModifierManager

        String inputPathFile1 = ConfigConstants.INPUT_FOLDER + file1;
        String inputPathFile2 = ConfigConstants.INPUT_FOLDER + file2;
        String outputPath = ConfigConstants.OUTPUT_FOLDER + ConfigConstants.OUTPUT_FILE;

        compareFiles(inputPathFile1, inputPathFile2, outputPath, ignoreDateTime);
    }
    
    private void compareFiles(String inputPath1, String inputPath2, String outputPath, boolean ignoreDateTime) {
        boolean foundDifference = false;
        int differenceNumber = 1;
        int index = 0;
        String line1 = FileIO.readLine(inputPath1, index);
        String line2 = FileIO.readLine(inputPath1, index);

        while ((line1 != null) && (line2 != null)) {
            index++;
            line1 = FileIO.readLine(inputPath1, index);
            line2 = FileIO.readLine(inputPath1, index);

            if (ignoreDateTime) {
                line1 = ModifierType.REMOVE_ALL_DATE_TIME.modify(line1);
                line2 = ModifierType.REMOVE_ALL_DATE_TIME.modify(line2);
            }

            if (!(line1.equals(line2))) {
                foundDifference = true;
                ConsoleLogger.logWhite(String.format("%s %d", StringConstants.DIFFERENCE_FOUND_LINE, index));
                FileIO.writeLine(outputPath, StringConstants.FILE_DIFFERENCE(differenceNumber++));
                FileIO.writeLine(outputPath, line1);
                FileIO.writeLine(outputPath, line2);
            }
        }

        if (foundDifference) {
            ConsoleLogger.logWhite(String.format("%s {%s}", StringConstants.DIFFERENCE_FOUND__OUTPUT_FILE, outputPath));
        } else {
            ConsoleLogger.logGreen(StringConstants.NO_DIFFERENCE_FOUND);
        }
    }
}
