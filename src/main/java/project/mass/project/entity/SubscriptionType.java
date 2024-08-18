package project.mass.project.entity;

public enum SubscriptionType {
    NONE("None"),
    BASIC("Basic"),
    PREMIUM("Premium"),
    UNLIMITED_CARE("Unlimited Care");

    private String value;
    SubscriptionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
