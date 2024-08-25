package project.mass.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import project.mass.project.Utility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "case_item")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "subject")
    @NotNull(message = "is required")
    @NotEmpty(message = "cannot be empty")
    private String subject;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull(message = "is required")
    private CaseStatus status;

    @Column(name = "opening_date")
    @NotNull(message = "is required")
    private LocalDate openingDate;

    @Column(name = "closing_date")
    private LocalDate closingDate;

    @OneToMany(mappedBy = "caseItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CaseTask> caseTasks = new ArrayList<>();

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

    public Case(String subject, CaseStatus status, LocalDate openingDate) {
        setSubject(subject);
        setStatus(status);
        setOpeningDate(openingDate);
        setClosingDate(null);
        setCaseTasks(null);
        setEmailMessages(null);
    }

    public Case(String subject, CaseStatus status, LocalDate openingDate, LocalDate closingDate, List<CaseTask> caseTasks, CustomerSupport customerSupport, List<EmailMessage> emailMessages) {
        setSubject(subject);
        setStatus(status);
        setOpeningDate(openingDate);
        setClosingDate(closingDate);
        setCaseTasks(caseTasks);
        setCustomerSupport(customerSupport);
        setEmailMessages(emailMessages);
    }

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        if (!Utility.validateString(subject)) {
            throw new IllegalArgumentException("Subject cannot be empty or null");
        }
        this.subject = subject;
    }

    public CaseStatus getStatus() {
        return status;
    }

    public void setStatus(CaseStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.status = status;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        if (openingDate == null) {
            throw new IllegalArgumentException("OpeningDate cannot be null");
        }
        this.openingDate = openingDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        if (closingDate != null && closingDate.isBefore(openingDate)) {
            throw new IllegalArgumentException("ClosingDate cannot be before openingDate");
        }
        this.closingDate = closingDate;
    }

    public List<CaseTask> getCaseTasks() {
        return caseTasks;
    }

    public void setCaseTasks(List<CaseTask> caseTasks) {
        if (caseTasks == null) {
            this.caseTasks = new ArrayList<>();
        } else if (Utility.hasNotNull(Collections.singletonList(caseTasks))) {
            this.caseTasks = new ArrayList<>();
            caseTasks.stream().filter(Objects::nonNull)
                    .forEach(cT -> this.caseTasks.add(cT));
        } else {
            this.caseTasks = new ArrayList<>();
        }
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
        if (emailMessages == null) {
            this.emailMessages = new ArrayList<>();
        } else if (Utility.hasNotNull(Collections.singletonList(emailMessages))) {
            emailMessages.stream().filter(Objects::nonNull)
                    .forEach(eM -> this.emailMessages.add(eM));
        } else {
            this.emailMessages = new ArrayList<>();
        }
    }
}
