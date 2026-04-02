package modification.textModification;

import constants.RegEx;

public class ReplaceFileExtension implements Modification {
  private final String newExtension;

  public ReplaceFileExtension(String newExtension) {
    this.newExtension = newExtension;
  }

  @Override
  public String applyTo(String text) {
    text = text.replaceFirst(RegEx.FILE_EXTENSION, "");
    return String.format("%s%s", text, this.newExtension);
  }
}
