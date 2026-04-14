package modification.textModification.auxiliaries;

import java.util.LinkedList;

public class CsvSplitter {
  private final char DELIMITER;
  private final char QUOTE = '"';

  public CsvSplitter(char delimiter) {
    if (delimiter == QUOTE) {
      throw new IllegalArgumentException("Delimiter must not be a quote (\")");
    }
    this.DELIMITER = delimiter;
  }

  public String[] split(String text) {
    int                nextDelimiter;
    int                nextQuote;
    int                offset;
    int                quoteOffset;
    LinkedList<String> list;

    list   = new LinkedList<>();
    offset = 0;

    while (((nextDelimiter = text.indexOf(this.DELIMITER, offset)) != -1)) {
      // Check for delimiters inside quotes and ignore them
      quoteOffset = offset;
      while (((nextQuote = text.indexOf(this.QUOTE, quoteOffset)) != -1) &&
             (nextQuote < nextDelimiter)) {
        nextQuote = text.indexOf(this.QUOTE, nextQuote + 1);

        if (nextQuote == -1) {
          break; // Ignore unclosed quotes
        }

        quoteOffset   = nextQuote + 1;
        nextDelimiter = text.indexOf(this.DELIMITER, quoteOffset);
        if (nextDelimiter == -1) {
          break;
        }
      }

      if (nextDelimiter == -1) {
        break;
      }
      list.add(text.substring(offset, nextDelimiter));
      offset = nextDelimiter + 1;
    }

    if (offset == 0) {
      return new String[] { text };
    }

    list.add(text.substring(offset, text.length()));

    String[] result = new String[list.size()];
    return list.toArray(result);
  }
}
