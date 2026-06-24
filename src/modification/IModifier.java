package modification;

import io.FileIO;
import modification.textModification.Modification;

public interface IModifier {
  void modifyContentOfFile(String fileName, Modification type);

  public static void appendFiles(String[] filePaths, String outputPath) {
    for (String path : filePaths) {
      FileIO.readAppend(path, outputPath);
    }
  }
}
