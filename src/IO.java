import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public interface IO {
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
            System.out.println("An error occurred. (reading)");
            e.printStackTrace();
            System.exit(-1);
        }

        if ((content.length() == 0)) {
            System.out.println("The file is empty.");
            System.exit(-1);
        }

        return content.toString();
    }
    
    public static void write(String path, String content) {
        try {
            FileWriter fileWriter = new FileWriter(path);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(content);

            bufferedWriter.close();
            
        } catch (IOException e) {
            System.out.println("An error occurred. (writing)");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
