package modification.textModification;

import constants.RegEx;

public class RemoveDate implements Modification {
  @Override
  public String applyTo(String text) {
    return text.replaceAll(RegEx.DATE_ISO8601, "");
  }
}
