
public interface Cleanser {
    public static void clearFile(String path, String regEx) {
        String newPath;
        String text;

        text = IO.read(path);
        text = text.replaceAll(regEx, "");
        newPath = path.replace(".txt", "_clean.txt");

        IO.write(newPath, text);
        System.out.println("Success! The new file is in " + newPath);
    }
}
