package io;

import constants.ConfigConstants;
import constants.RegEx;
import constants.StringConstants;

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
            FileReader fileReader = new FileReader(path);
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
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(content);
            bufferedWriter.close();

            ConsoleLogger.logGreen(String.format("%s {%s}", StringConstants.SUCCESS, path));

        } catch (IOException e) {
            ConsoleLogger.logError(StringConstants.ERROR_MSG + StringConstants.WHEN_WRITING, e);
        }
    }

    public static String readLine(String path, int skip) {
        String line;

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Skip lines
            for (int i = 0; i < skip; i++) {
                bufferedReader.readLine();
            }
            
            line = bufferedReader.readLine();

            bufferedReader.close();

        } catch (IOException e) {
            ConsoleLogger.logError(StringConstants.ERROR_MSG + StringConstants.WHEN_READING, e);
            return null;
        }
        return line;
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

    public static void partitionFile(String fileName, String outputPath, long sizeOfChunk, PartitionUnit unit) {
        String fileNameWithoutExtension = fileName.replaceFirst(RegEx.FILE_EXTENSION, "");
        String line;
        String newFileName;
        int newfileSize;
        int fileCounter = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            line = bufferedReader.readLine();
            while (line != null) {
                newFileName = String.format("%s%s-%03d%s", 
                                            ConfigConstants.OUTPUT_FOLDER,
                                            fileNameWithoutExtension, 
                                            ++fileCounter, 
                                            ConfigConstants.RESULT_EXTENSION);

                try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(newFileName))) {
                    newfileSize = 0;
                    while (line != null) {
                        byte[] bytes = (line + System.lineSeparator()).getBytes(ConfigConstants.DEFAULT_CHARSET);
                        if (newfileSize + bytes.length > (unit.getBytes(sizeOfChunk))) {
                            break;
                        }
                        outputStream.write(bytes);
                        newfileSize += bytes.length;
                        line = bufferedReader.readLine();
                    }
                }
            }
        } catch (IOException e) {
            ConsoleLogger.logError(StringConstants.ERROR_MSG + StringConstants.WHEN_PARTITIONING, e);
        }
    }

    private FileIO() {
        throw new IllegalStateException(StringConstants.UTILITY_CLASS);
    }
}
