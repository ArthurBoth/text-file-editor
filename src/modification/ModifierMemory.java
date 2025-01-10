package modification;

import constants.ConfigConstants;
import constants.StringConstants;
import io.ConsoleLogger;
import io.FileIO;

public class ModifierMemory extends Modifier{
    public ModifierMemory() {}

    @Override
    public void modifyContentOfFile(String fileName, ModifierType type) {
        String path    = ConfigConstants.INPUT_FOLDER  + fileName;
        String newPath = ConfigConstants.OUTPUT_FOLDER + ModifierType.REPLACE_EXTENSION.modify(fileName);
        String text;

        text = FileIO.read(path);

        if(text == null) return;

        text = type.modify(text);

        FileIO.write(newPath, text);
    }

    @Override
    public void compareFiles(String file1, String file2, boolean ignoreDateTime) {
        String text1 = FileIO.read(ConfigConstants.INPUT_FOLDER + file1);
        String text2 = FileIO.read(ConfigConstants.INPUT_FOLDER + file2);

        if (!(text1.equals(text2))) {
            ConsoleLogger.logWhite(StringConstants.DIFFERENCE_FOUND);
        } else {
            ConsoleLogger.logGreen(StringConstants.NO_DIFFERENCE_FOUND);
        }
    }
}
