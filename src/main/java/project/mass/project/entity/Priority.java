package project.mass.project.entity;

public enum Priority {
    NORMAL("Normal"),
    HIGH("High"),
    CRITICAL("Critical");

    private final String displayName;

    Priority(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
