package io.fileSizes;

public final class Megabyte extends FileSize {
  private final long bytes;

  public Megabyte(long megabytes) {
    super();
    this.bytes = megabytes << 20L;
  }

  @Override
  public long getBytes() {
    return this.bytes;
  }
}
