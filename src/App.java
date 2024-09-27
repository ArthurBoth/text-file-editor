import modification.FileModifier;

import static modification.ModifierType.TRANSCRIPTION;
import static modification.ModifierType.CENSOR;
import static modification.ModifierType.REPLACE_EXTENSION;
import static modification.ModifierType.REMOVE_TIME;
import static modification.ModifierType.REMOVE_ALL_DATE_TIME;
import static modification.ModifierType.REPLACE_SEMICOLON_CSV_DELIMITER;;

@SuppressWarnings("unused")
public class App{
    public static void main(String[] args) {
        // FileModifier.modifyContentOfFile("example.txt", TRANSCRIPTION);
        // FileModifier.modifyContentOfAllFiles(CENSOR);
        // FileModifier.renameFile("example.txt", REPLACE_EXTENSION);
        // FileModifier.renameFile("example.txt", "example.md");
        // FileModifier.renameAllFiles(REPLACE_EXTENSION);
        // FileModifier.compareFiles("example.txt", "example.md");
        }
}
