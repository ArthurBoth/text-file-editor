package io;

import constants.ConfigConstants;
import constants.RegEx;
import constants.StringConstants;
import io.fileSizes.FileSize;
import modification.textModification.Modification;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class FileIO {
  public static String read(String path) {
    StringBuilder content = new StringBuilder();

    try {
      FileReader     fileReader     = new FileReader(path);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line;

      while ((line = bufferedReader.readLine()) != null) {
        content.append(line);
        content.append(RegEx.NEW_LINE);
      }

      bufferedReader.close();
    } catch (IOException e) {
      ConsoleLogger.logError(StringConstants.ERROR_MSG + StringConstants.WHEN_READING, e);
      return null;
    }
    if ((content.length() == 0)) {
      ConsoleLogger.logGreen(StringConstants.EMPTY_FILE);
      return "";
    }

    return content.toString();
  }

  public static void write(String path, String content) {
    try {
      FileWriter     fileWriter     = new FileWriter(path);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

      bufferedWriter.write(content);
      bufferedWriter.close();

      ConsoleLogger.logGreen(StringConstants.SUCCESS(path));
    } catch (IOException e) {
      ConsoleLogger.logError(StringConstants.ERROR_MSG + StringConstants.WHEN_WRITING, e);
    }
  }

  public static void writeLine(String path, String line) {
    try {
      FileWriter fileWriter = new FileWriter(path, true);

      fileWriter.write(line);

      fileWriter.close();
    } catch (IOException e) {
      ConsoleLogger.logError(StringConstants.ERROR_MSG + StringConstants.WHEN_WRITING, e);
    }
  }

  public static void partitionFile(
    String fileName,
    String outputPath,
    FileSize unit,
    String nameFormat
  ) {
    String fileNameWithoutExtension = fileName.replaceFirst(RegEx.FILE_EXTENSION, "");
    String line;
    String newFileName;
    int newfileSize;
    int fileCounter = 0;

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ConfigConstants.INPUT_FOLDER + fileName))) {
      line = bufferedReader.readLine();
      while (line != null) {
        newFileName = String.format(
          nameFormat,
          outputPath,
          fileNameWithoutExtension,
          ++fileCounter,
          ConfigConstants.RESULT_EXTENSION
        );

        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(newFileName))) {
          newfileSize = 0;
          while (line != null) {
            byte[] bytes = (line + RegEx.NEW_LINE).getBytes(ConfigConstants.DEFAULT_CHARSET);
            if (newfileSize + bytes.length > (unit.getBytes())) {
              break;
            }
            outputStream.write(bytes);
            newfileSize += bytes.length;
            line         = bufferedReader.readLine();
          }
        }
      }
    } catch (IOException e) {
      ConsoleLogger.logError(StringConstants.ERROR_MSG + StringConstants.WHEN_PARTITIONING, e);
    }
  }

  public static void partitionFileOntoFolder(
    String fileName,
    String outputPath,
    FileSize unit,
    String nameFormat
  ) {
    String line;
    String newFileName;
    int newfileSize;
    int fileCounter = 0;
    byte[] csvHeader;
    byte[] bytes;

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ConfigConstants.INPUT_FOLDER + fileName))) {
      line      = bufferedReader.readLine();
      csvHeader = (line + RegEx.NEW_LINE).getBytes(ConfigConstants.DEFAULT_CHARSET);
      line      = bufferedReader.readLine(); // Goes to next line to not write header twice
      while (line != null) {
        newFileName = String.format(
          nameFormat,
          outputPath,
          fileCounter++,
          ConfigConstants.RESULT_EXTENSION
        );

        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(newFileName))) {
          if (csvHeader.length > (unit.getBytes())) {
            throw new IOException("CSV header too big for this chunk size");
          }

          outputStream.write(csvHeader);
          newfileSize = csvHeader.length;
          while (line != null) {
            bytes = (line + RegEx.NEW_LINE).getBytes(ConfigConstants.DEFAULT_CHARSET);
            if (newfileSize + bytes.length > (unit.getBytes())) {
              break;
            }
            outputStream.write(bytes);
            newfileSize += bytes.length;
            line         = bufferedReader.readLine();
          }
        }
      }
    } catch (IOException e) {
      ConsoleLogger.logError(StringConstants.ERROR_MSG + StringConstants.WHEN_PARTITIONING, e);
    }
  }

  public static void readAppend(String inputPath, String outputPath) {
    String line;
    FileWriter fileWriter;
    BufferedReader bufferedReader;

    try {
      bufferedReader = new BufferedReader(new FileReader(inputPath));
      fileWriter     = new FileWriter(outputPath, true);
      line           = bufferedReader.readLine();

      while (line != null) {
        fileWriter.write(String.format("%s%n", line));

        line = bufferedReader.readLine();
      }

      bufferedReader.close();
      fileWriter.close();
    } catch (IOException e) {
      ConsoleLogger.logError(StringConstants.ERROR_MSG + StringConstants.WHEN_APPENDING, e);
    }
    ConsoleLogger.logGreen(StringConstants.FINISHED_APPENDING(inputPath));
  }

  public static boolean readWrite(String inputPath, String outputPath, Modification modification) {
    String line;
    FileWriter fileWriter;
    BufferedReader bufferedReader;

    try {
      bufferedReader = new BufferedReader(new FileReader(inputPath));
      fileWriter     = new FileWriter(outputPath, true);
      line           = bufferedReader.readLine();

      while (line != null) {
        fileWriter.write(String.format("%s%n", modification.applyTo(line)));

        line = bufferedReader.readLine();
        }

      bufferedReader.close();
      fileWriter.close();
    } catch (IOException e) {
      ConsoleLogger.logError(StringConstants.ERROR_MSG + StringConstants.WHEN_REWRITING, e);
      return false;
    }
    return true;
  }

  public static void compareFiles(String path1, String path2, String outputPath) {
    BufferedWriter bufferedWriter;
    BufferedReader bufferedReader1;
    BufferedReader bufferedReader2;
    String         line1;
    String         line2;
    boolean        foundDifference;
    int            index;

    try {
      index           = 1;
      foundDifference = false;
      bufferedWriter  = new BufferedWriter(new FileWriter(outputPath, true));
      bufferedReader1 = new BufferedReader(new FileReader(path1));
      bufferedReader2 = new BufferedReader(new FileReader(path2));

      while (((line1 = bufferedReader1.readLine()) != null) &&
             ((line2 = bufferedReader2.readLine()) != null)) {
        if (!(line1.equals(line2))) {
          if (!foundDifference) {
            bufferedWriter.write(String.format("%s vs %s%n", path1, path2));
          }

          foundDifference = true;
          ConsoleLogger.log(String.format("%s %d", StringConstants.DIFFERENCE_FOUND_LINE, index));
          bufferedWriter.write(StringConstants.FILE_DIFFERENCE(index));
          bufferedWriter.write(String.format("%s%n", line1));
          bufferedWriter.write(String.format("%s%n", line2));
        }

        if ((index % ConfigConstants.LOG_LINES_STEP) == 0) {
          ConsoleLogger.log(StringConstants.LINES_PROCESSED(index));
        }
        index++;
      }

      bufferedReader1.close();
      bufferedReader2.close();
      bufferedWriter.close();
    } catch (IOException e) {
      ConsoleLogger.logError(StringConstants.ERROR_MSG + StringConstants.WHEN_PARTITIONING, e);
    }
  }

  private FileIO() {
    throw new IllegalStateException(StringConstants.UTILITY_CLASS);
  }
}
