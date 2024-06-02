import modification.Cleanser;

import static modification.ModifierType.TRANSCRIPTION;
import static modification.ModifierType.CENSOR;

public class App{
    public static void main(String[] args) {
        Cleanser.clearFile("example.txt", TRANSCRIPTION);
        Cleanser.clearFolder(CENSOR);
    }
}
