package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {
    public static String read(String path) {
        StringBuilder content = new StringBuilder();

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }

            bufferedReader.close();

        } catch (IOException e) {
            ConsoleLogger.logError("An error occurred. (reading)", e);
            return "";
        }
        if ((content.length() == 0)) {
            ConsoleLogger.logError("The file is empty.");
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
            
            ConsoleLogger.log("Success! The new file is in " + path);

        } catch (IOException e) {
            ConsoleLogger.logError("An error occurred. (writing)", e);
        }
    }

    private FileIO() {
        throw new IllegalStateException("Utility class");
    }
}
