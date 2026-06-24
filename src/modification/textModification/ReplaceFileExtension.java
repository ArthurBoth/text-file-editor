package modification.textModification;

public class ReplaceFileExtension implements Modification {
  private final String oldExtension;
  private final String newExtension;

  public ReplaceFileExtension(String oldExtension, String newExtension) {
    this.newExtension = newExtension;
    this.oldExtension = oldExtension;
  }

  private ReplaceFileExtension(Builder builder) {
    this.newExtension = builder.newExtension;
    this.oldExtension = builder.oldExtension;
  }

  @Override
  public String applyTo(String text) {
    return text.replaceFirst(this.oldExtension, this.newExtension);
  }

  public static class Builder {
    private String oldExtension;
    private String newExtension;

    public ReplaceFileExtension with(String newExtension) {
      this.newExtension = newExtension;
      return new ReplaceFileExtension(this);
    }

    public Builder (String oldExtension) {
      this.oldExtension = oldExtension;
      this.newExtension = "";
    }
  }
}
