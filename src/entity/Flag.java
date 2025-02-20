package entity;

public enum Flag {
    ADD("add"),
    UPDATE("update"),
    DELETE("delete"),
    LIST("list"),
    MARK_IN_PROGRESS("mark-in-progress"),
    MARK_DONE("mark-done");

    private final String value;

    Flag(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static Flag fromString(String text) {
        String normalized = text.toUpperCase().replace("-", "_");
        return Flag.valueOf(normalized);
    }
}
