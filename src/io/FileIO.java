package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import constants.RegEx;
import constants.StringConstants;

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
            ConsoleLogger.log(StringConstants.EMPTY_FILE);
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

            ConsoleLogger.log(String.format("%s {%s}", StringConstants.SUCCESS, path));

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
    
    public static void verifyFolders(String input, String output) {
        File inputFolder = new File(input);
        File outputFolder = new File(output);

        if (!inputFolder.exists()) {
            inputFolder.mkdir();
        }

        if (!outputFolder.exists()) {
            outputFolder.mkdir();
        }
    }

    private FileIO() {
        throw new IllegalStateException(StringConstants.UTILITY_CLASS);
    }
}
