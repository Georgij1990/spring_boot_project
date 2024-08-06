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
}
