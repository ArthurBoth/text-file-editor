package modification.textModification;

import constants.RegEx;

public class RemoveDateTime implements Modification {
  private RemoveDate dateRemover;
  private RemoveTime timeRemover;

  public RemoveDateTime() {
    this.dateRemover = new RemoveDate();
    this.timeRemover = new RemoveTime();
  }

  @Override
  public String applyTo(String text) {
    text = text.replaceAll(RegEx.DATE_TIME_ISO8601, "");
    text = dateRemover.applyTo(text);
    text = timeRemover.applyTo(text);
    return text;
  }
}
