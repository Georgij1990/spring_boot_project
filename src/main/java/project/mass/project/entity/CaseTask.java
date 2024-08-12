package project.mass.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "case_task")
public class CaseTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    @Column(name = "edited_status_reason")
    private String editedStatusReason;

    @ManyToOne
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

    public void setId(int id) {
        this.id = id;
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
}
