package modification;

import constants.StringConstants;
import io.ConsoleLogger;
import io.FileIO;
import io.fileSizes.FileSize;
import modification.textModification.Modification;
import constants.ConfigConstants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public abstract class FileModifier {
  private static IModifier memory     = new ModifierMemory();
  private static IModifier memoryless = new ModifierMemoryless();

  public static void modifyContentOfFile(String fileName, Modification type, boolean memorylessOperation) {
    if (fileName.equals(ConfigConstants.GIT_KEEP))
      return; // Skips the '.gitkeep' file
    verifyFolders();

    if (memorylessOperation) {
      memoryless.modifyContentOfFile(fileName, type);
    } else {
      memory.modifyContentOfFile(fileName, type);
    }
  }

  public static void modifyContentOfFile(String fileName, Modification type) {
    modifyContentOfFile(fileName, type, ConfigConstants.DEFAULT_MEMORYLESS_OPERATION_MODIFY_CONTENT);
  }

  public static void modifyContentOfAllFiles(Modification type) {
    modifyContentOfAllFiles(type, ConfigConstants.DEFAULT_MEMORYLESS_OPERATION_MODIFY_CONTENT);
  }

  public static void modifyContentOfAllFiles(Modification type, boolean memorylessOperations) {
    File   folder = new File(ConfigConstants.INPUT_FOLDER);
    File[] files  = folder.listFiles();

    for (File file : files) {
      if (file.isFile()) {
        modifyContentOfFile(file.getName(), type, memorylessOperations);
      }
    }
  }

  public static void renameFile(String oldName, String newName) {
    if (oldName.equals(ConfigConstants.GIT_KEEP))
      return; // Skips the '.gitkeep' file
    if (newName.equals(ConfigConstants.GIT_KEEP))
      return; // Skips the '.gitkeep' file
    verifyFolders();

    Path oldFile = Path.of(ConfigConstants.INPUT_FOLDER  + oldName);
    Path newFile = Path.of(ConfigConstants.OUTPUT_FOLDER + newName);

    try {
      Files.copy(oldFile, newFile);
    } catch (IOException e) {
      ConsoleLogger.logError(StringConstants.ERROR_MSG + StringConstants.WHEN_COPYING, e);
    }
  }

  public static void renameFile(String fileName, Modification modification) {
    renameFile(fileName, modification.applyTo(fileName));
  }

  public static void renameAllFiles(Modification modification) {
    File   folder = new File(ConfigConstants.INPUT_FOLDER);
    File[] files  = folder.listFiles();

    for (File file : files) {
      if (file.isFile()) {
        renameFile(file.getName(), modification);
      }
    }
  }

  public static void compareFiles(String file1, String file2) {
    if (file1.equals(ConfigConstants.GIT_KEEP))
      return; // Skips the '.gitkeep' file
    if (file2.equals(ConfigConstants.GIT_KEEP))
      return; // Skips the '.gitkeep' file
    verifyFolders();

    String inputPathFile1 = ConfigConstants.INPUT_FOLDER  + file1;
    String inputPathFile2 = ConfigConstants.INPUT_FOLDER  + file2;
    String outputPath     = ConfigConstants.OUTPUT_FOLDER + ConfigConstants.OUTPUT_FILE;

    FileIO.compareFiles(inputPathFile1, inputPathFile2, outputPath);
  }

  private static void verifyFolders() {
    File inputFolder  = new File(ConfigConstants.INPUT_FOLDER);
    File outputFolder = new File(ConfigConstants.OUTPUT_FOLDER);

    if (!inputFolder.exists()) {
      inputFolder.mkdir();
    }

    if (!outputFolder.exists()) {
      outputFolder.mkdir();
    }
  }

  public static void partitionFile(String fileName) {
    partitionFile(fileName, ConfigConstants.DEFAULT_PARTITION_UNIT);
  }

  public static void partitionFile(String fileName, FileSize unit) {
    File   file       = new File(ConfigConstants.INPUT_FOLDER + fileName);
    String nameFormat = StringConstants.PARTITION_NAME_FORMATTER(file.length(), unit.getBytes());
    FileIO.partitionFile(fileName, ConfigConstants.OUTPUT_FOLDER, unit, nameFormat);
  }


  public static void appendFiles(String outputName, String... fileNames) {
    String outputPath = ConfigConstants.OUTPUT_FOLDER + outputName;

    for (int i = 0; i < fileNames.length; i++) {
      fileNames[i] = ConfigConstants.INPUT_FOLDER + fileNames[i];
    }
    IModifier.appendFiles(fileNames, outputPath);
  }

  public static void appendFilesAllFiles(String outputName) {
    verifyFolders();

    File     folder    = new File(ConfigConstants.INPUT_FOLDER);
    File  [] files     = folder.listFiles();
    String[] fileNames = new String[files.length - 1];

    for (int i = 0; i < files.length; i++) {
      if (files[i].getName().equals(ConfigConstants.GIT_KEEP))
        continue;

      fileNames[i - 1] = files[i].getName();
    }

    Arrays.sort(fileNames);
    appendFiles(outputName, fileNames);
  }

  private FileModifier() {
    throw new IllegalStateException(StringConstants.UTILITY_CLASS);
  }
}
