package modification.textModification;

import constants.RegEx;

public class Transcription implements Modification {
  @Override
  public String applyTo(String text) {
    text = removeLine(text, RegEx.TIMESTAMP);
    text = removeLine(text, RegEx.SINGLE_NUMBER);
    text = reduceGaps(text);
    text = removeBeginningGap(text);
    return text;
  }

  private String removeLine(String text, String regex) {
    String[] lines = text.split(RegEx.NEW_LINE);

    for (int i = 0; i < lines.length; i++) {
      if (lines[i].matches(regex)) {
        lines[i] = "";
      }
    }
    return String.join(RegEx.NEW_LINE, lines);
  }

  private String reduceGaps(String text) {
    return text.replaceAll(RegEx.MULTIPLE_EMPTY_LINES, "\n\n");
  }

  private String removeBeginningGap(String text) {
    return text.replaceFirst(RegEx.MULTIPLE_EMPTY_LINES, "");
  }
}
