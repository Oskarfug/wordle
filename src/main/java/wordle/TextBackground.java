package wordle;

public enum TextBackground {
    GREEN("\u001B[1;42m"),
    YELLOW("\u001b[1;43m"),
    GRAY("\u001B[1;100m"),
    RESET("\u001B[0m");

    private final String escapeCode;

    TextBackground(String escapeCode) {
        this.escapeCode = escapeCode;
    }

    public String getEscapeCode() {
        return escapeCode;
    }
}
