package modification;

import constants.RegEx;
import constants.ConfigConstants;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashSet;
import java.util.LinkedList;

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

        @Override
        public String modify(String text, ModifierOptions options) {
            return modify(text);
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
                regex   = RegEx.REGEX_BOUNDARY + Pattern.quote(word) + RegEx.REGEX_BOUNDARY;
                pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE); // ignores case
                matcher = pattern.matcher(text);

                replacement = "*".repeat(word.length());

                text = matcher.replaceAll(replacement);
            }

            return text;
        }

        @Override
        public String modify(String text, ModifierOptions options) {
            return modify(text);
        }
    },
    REPLACE_EXTENSION {
        @Override
        public String modify(String text) {
            text = text.replaceFirst(RegEx.FILE_EXTENSION, "");
            return String.format("%s%s", text, ConfigConstants.RESULT_EXTENSION);
        }

        @Override
        public String modify(String text, ModifierOptions options) {
            return modify(text);
        }
    },
    REMOVE_TIME {
        @Override
        public String modify(String text) {
            return text.replaceAll(RegEx.TIME_ISO8601, "");
        }

        @Override
        public String modify(String text, ModifierOptions options) {
            return modify(text);
        }
    },
    REMOVE_ALL_DATE_TIME {
        @Override
        public String modify(String text) {
            text = text.replaceAll(RegEx.DATE_TIME_ISO8601, "");
            text = text.replaceAll(RegEx.DATE_ISO8601, "");
            text = text.replaceAll(RegEx.TIME_ISO8601, "");
            return text;
        }

        @Override
        public String modify(String text, ModifierOptions options) {
            return modify(text);
        }
    },
    REPLACE_SEMICOLON_CSV_DELIMITER {
        @Override
        public String modify(String text) {
            text = text.replace(ConfigConstants.CSV_NEW_SEPARATOR, ConfigConstants.CSV_PIVOT_SEPARATOR);
            text = text.replace(ConfigConstants.CSV_CURRENT_SEPARATOR, ConfigConstants.CSV_NEW_SEPARATOR);
            return text;
        }

        @Override
        public String modify(String text, ModifierOptions options) {
            return modify(text);
        }
    },
    REMOVE_CSV_COLUMNS {
        @Override
        public String modify(String text) {
            return text;
        }

        @Override
        public String modify(String text, ModifierOptions options) {
            int[] columns = options.getNumbers();
            return csvReplaceColumns(text, columns);
        }

        private static String csvReplaceColumns(String text, int[] columns) {
            String[] split;
            HashSet<Integer> columnSet;
            LinkedList<String> splittedReturn;

            split = text.split(ConfigConstants.CSV_CURRENT_SEPARATOR);
            if (split.length <= columns.length) return "";
        
            columnSet = new HashSet<>();
            for (int i : columns) columnSet.add(i);
        
            splittedReturn = new LinkedList<>();
            for (int i = 0; i < split.length; i++) {
                if(columnSet.contains(i)) continue;
                
                splittedReturn.add(split[i]);
            }

            return String.join(ConfigConstants.CSV_CURRENT_SEPARATOR, splittedReturn);
        }
    };

    public abstract String modify(String text);
    public abstract String modify(String text, ModifierOptions options);

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
