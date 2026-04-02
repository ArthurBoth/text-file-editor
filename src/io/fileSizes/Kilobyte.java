package io.fileSizes;

public final class Kilobyte extends FileSize {
  private final long bytes;

  public Kilobyte(long kilobytes) {
    super();
    this.bytes = kilobytes << 10L;
  }

  @Override
  public long getBytes() {
    return this.bytes;
  }
}
