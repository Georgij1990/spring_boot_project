package project.mass.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "case_item")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "subject")
    private String subject;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CaseStatus status;

    @Column(name = "opening_date")
    private LocalDate openingDate;

    @Column(name = "closing_date")
    private LocalDate closingDate;

    @OneToMany(mappedBy = "caseItem")
    private List<CaseTask> caseTasks;

    @ManyToOne
    @JoinColumn(name = "customer_support_id")
    private CustomerSupport customerSupport;

    @ManyToMany
    @JoinTable(
            name = "case_email_message",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "email_message_id")
    )
    private List<EmailMessage> emailMessages;
}
