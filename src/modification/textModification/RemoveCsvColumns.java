package modification.textModification;

import java.util.LinkedList;
import java.util.Set;

import constants.RegEx;
import modification.textModification.auxiliaries.CsvSplitter;

public class RemoveCsvColumns implements Modification {
  private final Set<Integer> columns;
  private final CsvSplitter  csvSplitter;
  private final Character    delimiter;

  public RemoveCsvColumns(Character delimiter, Set<Integer> columns) {
    this.columns     = columns;
    this.delimiter   = delimiter;
    this.csvSplitter = new CsvSplitter(delimiter);
  }

    // Flase Builder Pattern
  public RemoveCsvColumns usingAsDelimiter(Character delimiter) {
    return new RemoveCsvColumns(delimiter, this.columns);
  }

  @Override
  public String applyTo(String text) {
    String[]           rows;
    String[]           split;
    LinkedList<String> splittedReturn;

    rows = text.split(RegEx.NEW_LINE);

    for (int i = 0; i < rows.length; i++) {
      split = csvSplitter.split(rows[i]);

      if (split.length <= this.columns.size())
        return "";

      splittedReturn = new LinkedList<>();
      for (int ii = 0; ii < split.length; ii++) {
        if (columns.contains(ii))
          continue;

        splittedReturn.add(split[ii]);
      }

      rows[i] = String.join(String.valueOf(delimiter), splittedReturn);
    }

    return String.join(RegEx.NEW_LINE, rows);
  }
}
