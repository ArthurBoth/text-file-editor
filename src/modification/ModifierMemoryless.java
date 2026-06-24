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
}
