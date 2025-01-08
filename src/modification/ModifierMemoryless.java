package modification;

import constants.ConfigConstants;
import constants.StringConstants;
import io.ConsoleLogger;
import io.FileIO;

public class ModifierMemoryless extends Modifier {
    public ModifierMemoryless() {}

    @Override
    public void modifyContentOfFile(String fileName, ModifierType type) {
        String path = ConfigConstants.INPUT_FOLDER + fileName;
        String newPath = ConfigConstants.OUTPUT_FOLDER + ModifierType.REPLACE_EXTENSION.modify(fileName);

        readWrite(path, newPath, type);
    }

    private void readWrite(String inputPath, String outputPath, ModifierType type) {
        int index = 0;
        String line = FileIO.readLine(inputPath, index++);

        if (line == null) {
            ConsoleLogger.logWhite(StringConstants.EMPTY_FILE);
            return;
        }
        
        while (line != null) {
            line = FileIO.readLine(inputPath, index++);
            line = String.format("%s%n", type.modify(line));
            FileIO.writeLine(outputPath, line);
        }
        ConsoleLogger.logGreen(String.format("%s {%s}", StringConstants.SUCCESS, outputPath));
    }

    public void compareFiles(String file1, String file2, boolean ignoreDateTime) {
        String inputPathFile1 = ConfigConstants.INPUT_FOLDER + file1;
        String inputPathFile2 = ConfigConstants.INPUT_FOLDER + file2;
        String outputPath = ConfigConstants.OUTPUT_FOLDER + ConfigConstants.OUTPUT_FILE;

        compareFiles(inputPathFile1, inputPathFile2, outputPath, ignoreDateTime);
    }
    
    private void compareFiles(String inputPath1, String inputPath2, String outputPath, boolean ignoreDateTime) {
        boolean foundDifference = false;
        int differenceNumber = 0;
        int index = 0;
        String line1 = FileIO.readLine(inputPath1, index);
        String line2 = FileIO.readLine(inputPath2, index);

        while ((line1 != null) && (line2 != null)) {
            if (ignoreDateTime) {
                line1 = ModifierType.REMOVE_ALL_DATE_TIME.modify(line1);
                line2 = ModifierType.REMOVE_ALL_DATE_TIME.modify(line2);
            }

            if (!(line1.equals(line2))) {
                foundDifference = true;
                ConsoleLogger.logWhite(String.format("%s %d", StringConstants.DIFFERENCE_FOUND_LINE, index));
                FileIO.writeLine(outputPath, StringConstants.FILE_DIFFERENCE(++differenceNumber, index));
                FileIO.writeLine(outputPath, String.format("%s%n",line1));
                FileIO.writeLine(outputPath, String.format("%s%n",line2));
            }
            
            index++;
            line1 = FileIO.readLine(inputPath1, index);
            line2 = FileIO.readLine(inputPath2, index);
        }

        if (foundDifference) {
            ConsoleLogger.logWhite(String.format("%s {%s}", StringConstants.DIFFERENCE_FOUND__OUTPUT_FILE, outputPath));
        } else {
            ConsoleLogger.logGreen(StringConstants.NO_DIFFERENCE_FOUND);
        }
    }
}
