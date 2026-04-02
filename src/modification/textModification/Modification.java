package modification.textModification;

import java.util.HashSet;

import constants.ConfigConstants;
import constants.RegEx;

@FunctionalInterface
public interface Modification {
  String applyTo(String text);

  default Modification andThen(Modification other) {
    return text -> other.applyTo(this.applyTo(text));
  }

  static ReplaceCsvDelimiter replaceCsvDelimiter() {
    return new ReplaceCsvDelimiter(ConfigConstants.CSV_CURRENT_SEPARATOR, ConfigConstants.CSV_NEW_SEPARATOR);
  }

  static ReplaceCsvDelimiter.Builder replaceCsvDelimiter(String delimiter) {
    return new ReplaceCsvDelimiter.Builder(delimiter);
  }

  static Transcription transcription() {
    return new Transcription();
  }

  static Censor censor() {
    return new Censor(RegEx.BAD_WORDS);
  }

  static Censor censor(String... words) {
    HashSet<String> set = HashSet.newHashSet(words.length);
    for (String i : words) {
      set.add(i);
    }
    return new Censor(set);
  }

  static ReplaceFileExtension replaceExtension() {
    return new ReplaceFileExtension(ConfigConstants.RESULT_EXTENSION);
  }

  static ReplaceFileExtension replaceExtensionWith(String extension) {
    return new ReplaceFileExtension(extension);
  }

  static RemoveTime removeTime() {
    return new RemoveTime();
  }

  static RemoveDate removeDate() {
    return new RemoveDate();
  }

  static RemoveDateTime removeDateTime() {
    return new RemoveDateTime();
  }

  static RemoveCsvColumns removeColumns(int... columns) {
    HashSet<Integer> set = HashSet.newHashSet(columns.length);
    for (Integer i : columns) {
      set.add(i);
    }
    return new RemoveCsvColumns(set);
  }
}
