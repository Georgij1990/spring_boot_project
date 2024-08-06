package project.mass.project.entity;

public enum CaseStatus {
    NEW("New"),
    WORKED("Worked"),
    ESCALATED("Escalated"),
    CLOSED("Closed");

    private String status;

    CaseStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
