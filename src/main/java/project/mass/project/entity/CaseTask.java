package project.mass.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "case_task")
public class CaseTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotNull(message = "is required")
    @NotEmpty(message = "cannot be empty")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull(message = "is required")
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    @NotNull(message = "is required")
    private Priority priority;

    @Column(name = "edited_status_reason")
    private String editedStatusReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id")
    private Case caseItem;

    public CaseTask() {
    }

    public CaseTask(String name, Status status, Priority priority, String editedStatusReason, Case caseItem) {
        this.name = name;
        this.status = status;
        this.priority = priority;
        this.editedStatusReason = editedStatusReason;
        this.caseItem = caseItem;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getEditedStatusReason() {
        return editedStatusReason;
    }

    public void setEditedStatusReason(String editedStatusReason) {
        this.editedStatusReason = editedStatusReason;
    }

    public Case getCaseItem() {
        return caseItem;
    }

    public void setCaseItem(Case caseItem) {
        this.caseItem = caseItem;
    }

    @PrePersist
    @PreUpdate
    private void validateCaseTask() {
        if (caseItem == null) {
            throw new IllegalStateException("CaseItem cannot be null");
        }
    }

    @Override
    public String toString() {
        return "CaseTask{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                ", editedStatusReason='" + editedStatusReason + '\'' +
                ", caseItem=" + caseItem +
                '}';
    }
}
