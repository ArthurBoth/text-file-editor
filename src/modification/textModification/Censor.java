package modification.textModification;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import constants.RegEx;

public class Censor implements Modification {
  private final Set<String> expressions;

  public Censor(Set<String> expressions) {
    this.expressions = expressions;
  }

  @Override
  public String applyTo(String text) {
    String regex;
    String replacement;
    Pattern pattern;
    Matcher matcher;

    for (String expression : this.expressions) {
      regex   = RegEx.REGEX_BOUNDARY + Pattern.quote(expression) + RegEx.REGEX_BOUNDARY;
      pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
      matcher = pattern.matcher(text);

      replacement = "*".repeat(expression.length());

      text = matcher.replaceAll(replacement);
    }

    return text;
  }
}
