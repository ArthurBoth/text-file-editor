package modification;

import io.FileIO;

public abstract class Modifier {
    public abstract void modifyContentOfFile(String fileName, ModifierType type);
    
    public abstract void compareFiles(String file1, String file2, boolean ignoreDateTime);

    public static void partitionFile(String fileName, String outputPath, long sizeOfChunkMB) {
        FileIO.partitionFile(fileName, outputPath, sizeOfChunkMB);
    }
}
