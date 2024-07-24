package modification;

public abstract class Modifier {
    public abstract void modifyContentOfFile(String fileName, ModifierType type);

    public abstract void renameFile(String fileName, ModifierType type);

    public abstract void renameFile(String oldFileName, String newFileName);
}
