package modification.textModification;

import java.util.Set;

public class ReplaceCsvDelimiter implements Modification {
  private final String oldDelimiter;
  private final String newDelimiter;

  public ReplaceCsvDelimiter(String oldDelimiter, String newDelimiter) {
    this.oldDelimiter = oldDelimiter;
    this.newDelimiter = newDelimiter;
  }

  public static class Builder {
    private final String oldDelimiter;

    public ReplaceCsvDelimiter with(String newDelimiter) {
      return new ReplaceCsvDelimiter(this.oldDelimiter, newDelimiter);
    }

    public Builder(String oldDelimiter) {
      this.oldDelimiter = oldDelimiter;
    }
  }

  @Override
  public String applyTo(String text) {
    Set<String> separators = Set.of(",", ";", "|", "/", "\\", ".");
    String[] columns;

    separators.remove(this.oldDelimiter);
    separators.remove(this.newDelimiter);

    columns = text.split(this.oldDelimiter); // TODO use a smart splitter next

    if (text.contains(this.newDelimiter)) {
      for (String pivot : separators) {
        if (text.contains(pivot))
          continue;

        for (int i = 0; i < columns.length; i++) {
          columns[i] = columns[i].replace(this.newDelimiter, pivot);
        }
        break;
      }
    }
    return String.join(this.newDelimiter, columns);
  }
}