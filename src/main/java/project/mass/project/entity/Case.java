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

    @NotNull(message = "is required")
    @NotEmpty(message = "cannot be empty")
    @Column(name = "subject")
    private String subject;

    @NotNull(message = "is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CaseStatus status;

    @NotNull(message = "is required")
    @Column(name = "opening_date")
    private LocalDate openingDate;

    @Column(name = "closing_date")
    private LocalDate closingDate;

    @NotNull
    @OneToMany(mappedBy = "caseItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CaseTask> caseTasks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_support_id")
    private CustomerSupport customerSupport;

    @NotNull
    @ManyToMany
    @JoinTable(
            name = "case_email_message",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "email_message_id")
    )
    private List<EmailMessage> emailMessages = new ArrayList<>();

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
            throw new IllegalArgumentException("ClosingDate cannot be before openingDate or null");
        }
        this.closingDate = closingDate;
    }

    public List<CaseTask> getCaseTasks() {
        return List.copyOf(caseTasks);
    }

    public void setCaseTasks(List<CaseTask> caseTasks) {
        if (Utility.hasNotNull(Collections.singletonList(caseTasks))) {
            caseTasks.stream().filter(Objects::nonNull).forEach(cT -> this.caseTasks.add(cT));
        }
    }

    public void addCaseTask(CaseTask cT) {
        if (cT == null) {
            throw new IllegalArgumentException("Case task cannot be null");
        }
        this.caseTasks.add(cT);
    }

    public void removeCaseTask(CaseTask cT) {
        if (cT == null) {
            throw new IllegalArgumentException("Case task cannot be null");
        }
        this.caseTasks.remove(cT);
    }

    public CustomerSupport getCustomerSupport() {
        return customerSupport;
    }

    public void setCustomerSupport(CustomerSupport customerSupport) {
        this.customerSupport = customerSupport;
    }

    public List<EmailMessage> getEmailMessages() {
        return List.copyOf(emailMessages);
    }

    public void setEmailMessages(List<EmailMessage> emailMessages) {
        if (Utility.hasNotNull(Collections.singletonList(emailMessages))) {
            emailMessages.stream().filter(Objects::nonNull).forEach(eM -> this.emailMessages.add(eM));
        }
    }

    public void addEmailMessage(EmailMessage em) {
        if (em == null) {
            throw new IllegalArgumentException("Email message cannot be null");
        }
        this.emailMessages.add(em);
    }

    public void removeEmailMessage(EmailMessage em) {
        if (em == null) {
            throw new IllegalArgumentException("Email message cannot be null");
        }
        this.emailMessages.remove(em);
    }
}
