package modification.textModification;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import modification.textModification.auxiliaries.CsvSplitter;

public class ReplaceCsvDelimiter implements Modification {
  private final Character   oldDelimiter;
  private final Character   newDelimiter;
  private final CsvSplitter csvSplitter;

  public ReplaceCsvDelimiter(Character oldDelimiter, Character newDelimiter) {
    this.oldDelimiter = oldDelimiter;
    this.newDelimiter = newDelimiter;
    this.csvSplitter  = new CsvSplitter(this.oldDelimiter);
  }

  @Override
  public String applyTo(String text) {
    Set<Character> separators;
    String[]       columns;

    separators = Stream.of(',', ';', '|', '/', '\\', '.')
                        .filter(e -> e != this.oldDelimiter)
                        .filter(e -> e != this.newDelimiter)
                        .collect(Collectors.toSet());

    columns = csvSplitter.split(text);

    if (text.contains(String.valueOf(this.newDelimiter))) {
      for (Character pivot : separators) {
        if (text.contains(String.valueOf(pivot)))
          continue;

        for (int i = 0; i < columns.length; i++) {
          columns[i] = columns[i].replace(String.valueOf(this.newDelimiter), String.valueOf(pivot));
        }
        break;
      }
    }
    return String.join(String.valueOf(this.newDelimiter), columns);
  }

  public static class Builder {
    private final char oldDelimiter;

    public ReplaceCsvDelimiter with(char newDelimiter) {
      return new ReplaceCsvDelimiter(this.oldDelimiter, newDelimiter);
    }

    public ReplaceCsvDelimiter with(String newDelimiter) {
      if (newDelimiter.length() != 1) {
        throw new IllegalArgumentException("Delimiter must be a single character");
      }
      return new ReplaceCsvDelimiter(this.oldDelimiter, newDelimiter.charAt(0));
    }

    public Builder(char oldDelimiter) {
      this.oldDelimiter = oldDelimiter;
    }
  }
}
