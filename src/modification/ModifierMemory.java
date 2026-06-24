package modification;

import constants.ConfigConstants;
import io.FileIO;
import modification.textModification.Modification;

public class ModifierMemory implements IModifier {
  @Override
  public void modifyContentOfFile(String fileName, Modification type) {
    String path    = ConfigConstants.INPUT_FOLDER  + fileName;
    String newPath = ConfigConstants.OUTPUT_FOLDER + fileName;
    String text;

    text = FileIO.read(path);

    if (text == null)
      return;

    text = type.applyTo(text);

    FileIO.write(newPath, text);
  }
}
