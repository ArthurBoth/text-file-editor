package modification.textModification;

import constants.RegEx;

public class RemoveTime implements Modification {
  @Override
  public String applyTo(String text) {
    return text.replaceAll(RegEx.TIME_ISO8601, "");
  }
}
