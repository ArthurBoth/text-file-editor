package modification;

import constants.ConfigConstants;
import constants.StringConstants;
import io.ConsoleLogger;
import io.FileIO;
import modification.textModification.Modification;

public class ModifierMemoryless implements IModifier {
  @Override
  public void modifyContentOfFile(String fileName, Modification type) {
    String inputPath  = ConfigConstants.INPUT_FOLDER  + fileName;
    String outputPath = ConfigConstants.OUTPUT_FOLDER + fileName;

    if (FileIO.readWrite(inputPath, outputPath, type))
      ConsoleLogger.logGreen(StringConstants.SUCCESS(outputPath));
  }

  public void compareFiles(String file1, String file2, boolean ignoreDateTime) {
    String  inputPathFile1   = ConfigConstants.INPUT_FOLDER  + file1;
    String  inputPathFile2   = ConfigConstants.INPUT_FOLDER  + file2;
    String  outputPath       = ConfigConstants.OUTPUT_FOLDER + ConfigConstants.OUTPUT_FILE;
    boolean foundDifference  = false;
    int     differenceNumber = 0;
    int     index            = 0;
    String  line1            = FileIO.readLine(inputPathFile1, index);
    String  line2            = FileIO.readLine(inputPathFile2, index);

    while ((line1 != null) && (line2 != null)) {
      if (ignoreDateTime) {
        line1 = Modification.removeDateTime().applyTo(line1);
        line2 = Modification.removeDateTime().applyTo(line2);
      }

      if (!(line1.equals(line2))) {
        if (!foundDifference) {
          FileIO.writeLine(outputPath, String.format("%s vs %s%n", inputPathFile1, inputPathFile2));
        }
        foundDifference = true;
        ConsoleLogger.log(String.format("%s %d", StringConstants.DIFFERENCE_FOUND_LINE, index));
        FileIO.writeLine(outputPath, StringConstants.FILE_DIFFERENCE(++differenceNumber, index));
        FileIO.writeLine(outputPath, String.format("%s%n", line1));
        FileIO.writeLine(outputPath, String.format("%s%n", line2));
      }

      index++;
      line1 = FileIO.readLine(inputPathFile1, index);
      line2 = FileIO.readLine(inputPathFile2, index);
    }

    if (foundDifference) {
      ConsoleLogger.log(String.format("%s {%s}", StringConstants.DIFFERENCE_FOUND__OUTPUT_FILE, outputPath));
    } else {
      ConsoleLogger.logGreen(StringConstants.NO_DIFFERENCE_FOUND);
    }
  }
}
