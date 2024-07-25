package modification;

import constants.RegEx;
import constants.ConfigConstants;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public enum ModifierType {
    TRANSCRIPTION {
        @Override
        public String modify(String text) {
            text = removeLine(text, RegEx.TIMESTAMP);
            text = removeLine(text, RegEx.SINGLE_NUMBER);
            text = reduceGaps(text);
            text = removeBeginningGap(text);
            return text;
        }
    },
    CENSOR {
        @Override
        public String modify(String text) {
            String regex;
            String replacement;
            Pattern pattern;
            Matcher matcher;
            
            for (String word : RegEx.BAD_WORDS) {
                regex = RegEx.REGEX_BOUNDARY + Pattern.quote(word) + RegEx.REGEX_BOUNDARY;
                pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE); // ignores case
                matcher = pattern.matcher(text);

                replacement = "*".repeat(word.length());

                text = matcher.replaceAll(replacement);
            }

            return text;
        }
    },
    REPLACE_EXTENSION {
        @Override
        public String modify(String text) {
            return text.replaceFirst(RegEx.FILE_EXTENSION, ConfigConstants.RESULT_EXTENSION);
        }
    },
    REMOVE_DATE_TIME {
        @Override
        public String modify(String text) {
            return text.replaceAll(RegEx.DATE_TIME_ISO8601, "");
        }
    },
    REMOVE_ALL_DATE_TIME {
        @Override
        public String modify(String text) {
            text.replaceAll(RegEx.DATE_TIME_ISO8601, "");
            text.replaceAll(RegEx.DATE_ISO8601, "");
            text.replaceAll(RegEx.TIME_ISO8601, "");
            return text;
        }
    };

    public abstract String modify(String text);

    private static String removeLine(String text, String regex) {
        String[] lines = text.split(RegEx.NEW_LINE);

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].matches(regex)) {
                lines[i] = "";
            }
        }
        return String.join(RegEx.NEW_LINE, lines);
    }

    private static String reduceGaps(String text) {
        return text.replaceAll(RegEx.MULTIPLE_EMPTY_LINES, "\n\n");
    }

    private static String removeBeginningGap(String text) {
        return text.replaceFirst(RegEx.MULTIPLE_EMPTY_LINES, "");
    }
}
