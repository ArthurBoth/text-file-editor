package modification.textModification;

import java.util.LinkedList;
import java.util.Set;

import constants.ConfigConstants;
import constants.RegEx;

public class RemoveCsvColumns implements Modification {
  private Set<Integer> columns;

  public RemoveCsvColumns(Set<Integer> columns) {
    this.columns = columns;
  }

  @Override
  public String applyTo(String text) {
    String[] rows;
    String[] split;
    LinkedList<String> splittedReturn;

    rows = text.split(RegEx.NEW_LINE);

    for (int i = 0; i < rows.length; i++) {
      split = rows[i].split(ConfigConstants.CSV_CURRENT_SEPARATOR);

      if (split.length <= this.columns.size())
        return "";

      splittedReturn = new LinkedList<>();
      for (int ii = 0; ii < split.length; ii++) {
        if (columns.contains(ii))
          continue;

        splittedReturn.add(split[ii]);
      }

      rows[i] = String.join(ConfigConstants.CSV_CURRENT_SEPARATOR, splittedReturn);
    }

    return String.join(RegEx.NEW_LINE, rows);
  }
}
