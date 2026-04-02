package io.fileSizes;

public sealed abstract class FileSize permits Kilobyte, Megabyte, Gigabyte {
  public abstract long getBytes();

  public static FileSize KB(long kilobytes) {
    return new Kilobyte(kilobytes);
  }

  public static FileSize MB(long megabytes) {
    return new Megabyte(megabytes);
  }

  public static FileSize GB(long gigabytes) {
    return new Gigabyte(gigabytes);
  }
}
