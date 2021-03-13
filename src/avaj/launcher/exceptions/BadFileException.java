package avaj.launcher.exceptions;

public class BadFileException extends RuntimeException {

    public BadFileException(String fileName) {
        super("File not found: " + fileName);
    }

    public BadFileException(int lineNumber, String line) {
        super(createMessage(lineNumber, line));
    }

    private static String createMessage(int lineNumber, String line) {
        if (line == null) {
            return "Bad file";
        } else  {
            return "Bad file in line " + lineNumber + " [" + line + "]";
        }
    }
}
