package io.fileSizes;

public final class Gigabyte extends FileSize {
  private final long bytes;

  public Gigabyte(long gigabytes) {
    super();
    this.bytes = gigabytes << 30L;
  }

  @Override
  public long getBytes() {
    return this.bytes;
  }
}
