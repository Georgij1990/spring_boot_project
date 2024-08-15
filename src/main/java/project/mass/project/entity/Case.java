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

    @OneToMany(mappedBy = "caseItem", cascade = CascadeType.ALL, orphanRemoval = true)
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

    public Case() {}

    public Case(String subject, CaseStatus status, LocalDate openingDate, LocalDate closingDate, List<CaseTask> caseTasks, CustomerSupport customerSupport, List<EmailMessage> emailMessages) {
        this.subject = subject;
        this.status = status;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.caseTasks = caseTasks;
        this.customerSupport = customerSupport;
        this.emailMessages = emailMessages;
    }

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public CaseStatus getStatus() {
        return status;
    }

    public void setStatus(CaseStatus status) {
        this.status = status;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public List<CaseTask> getCaseTasks() {
        return caseTasks;
    }

    public void setCaseTasks(List<CaseTask> caseTasks) {
        this.caseTasks = caseTasks;
    }

    public CustomerSupport getCustomerSupport() {
        return customerSupport;
    }

    public void setCustomerSupport(CustomerSupport customerSupport) {
        this.customerSupport = customerSupport;
    }

    public List<EmailMessage> getEmailMessages() {
        return emailMessages;
    }

    public void setEmailMessages(List<EmailMessage> emailMessages) {
        this.emailMessages = emailMessages;
    }
}
