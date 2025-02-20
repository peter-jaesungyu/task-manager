package entity;

public enum Status {
    TODO("todo"),
    IN_PROGRESS("in-progress"),
    DONE("done");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static Status fromString(String text) {
        String normalized = text.toUpperCase().replace("-", "_");
        return Status.valueOf(normalized);
    }
}
