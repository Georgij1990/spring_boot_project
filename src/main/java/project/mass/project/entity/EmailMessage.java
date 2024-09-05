package project.mass.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import project.mass.project.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "email_message")
public class EmailMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "from_address")
    private String fromAddress;

    @ElementCollection
    @CollectionTable(name = "to_adresses", joinColumns = @JoinColumn(name = "email_message_id"))
    @Column(name = "to_address")
    private List<String> to = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "cc_adresses", joinColumns = @JoinColumn(name = "email_message_id"))
    @Column(name = "cc_address")
    private List<String> cc = new ArrayList<>();

    @Column(name = "subject")
    private String subject;

    @Column(name = "email_message")
    private String message;

    @NotNull
    @ManyToMany
    @JoinTable(
            name = "case_email_message",
            joinColumns = @JoinColumn(name = "email_message_id"),
            inverseJoinColumns = @JoinColumn(name = "case_id")
    )
    private List<Case> cases = new ArrayList<>();

    @NotNull
    @ManyToMany
    @JoinTable(
            name = "email_message_email_attachment",
            joinColumns = @JoinColumn(name = "email_message_id"),
            inverseJoinColumns = @JoinColumn(name = "email_attachment_id")
    )
    private List<EmailAttachment> emailAttachments = new ArrayList<>();

    public EmailMessage() {
    }

    public EmailMessage(String fromAddress) {
        setFromAddress(fromAddress);
    }

    public EmailMessage(List<String> to) {
        setTo(to);
    }

    public int getId() {
        return id;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public List<String> getTo() {
        return List.copyOf(this.to);
    }

    public void setTo(List<String> toList) {
        if (Utility.hasNotNull(Collections.singletonList(toList))) {
            toList.stream().filter(Objects::nonNull).forEach(tA -> this.to.add(tA));
        }
    }

    public void addToAddress(String toAddress) {
        if (!Utility.validateString(toAddress)) {
            throw new IllegalArgumentException("The toAddress that you want to add cannot be null");
        }
        this.to.add(toAddress);
    }

    public void removeToAddress(String toAddress) {
        if (!Utility.validateString(toAddress)) {
            throw new IllegalArgumentException("The toAddress that you want to remove cannot be null");
        }
        this.to.remove(toAddress);
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Case> getCases() {
        return List.copyOf(cases);
    }

    public void setCases(List<Case> cases) {
        if (Utility.hasNotNull(Collections.singletonList(cases))) {
            cases.stream().filter(Objects::nonNull).forEach(c -> this.cases.add(c));
        }
    }

    public void addCase(Case caseItem) {
        if (caseItem == null) {
            throw new IllegalArgumentException("The caseItem that you want to add cannot be null");
        }
        this.cases.add(caseItem);
    }

    public void removeCase(Case caseItem) {
        if (caseItem == null) {
            throw new IllegalArgumentException("The caseItem that you want to delete cannot be null.");
        }
        this.cases.remove(caseItem);
    }

    public List<EmailAttachment> getEmailAttachments() {
        return List.copyOf(emailAttachments);
    }

    public void setEmailAttachments(List<EmailAttachment> emailAttachments) {
        if (Utility.hasNotNull(Collections.singletonList(emailAttachments))) {
            emailAttachments.stream().filter(Objects::nonNull).forEach(eA -> this.emailAttachments.add(eA));
        }
    }

    public void addEmailAttachment(EmailAttachment emailAttachment) {
        if (emailAttachment == null) {
            throw new IllegalArgumentException("The emailAttachment that you want to add cannot be null");
        }
        this.emailAttachments.add(emailAttachment);
    }

    public void removeEmailAttachment(EmailAttachment emailAttachment) {
        if (emailAttachment == null) {
            throw new IllegalArgumentException("The emailAttachment that you want to remove cannot be null");
        }
        this.emailAttachments.remove(emailAttachment);
    }

    private void validateEmailMessage() {
        boolean isFromAddressEmpty = (fromAddress == null || fromAddress.trim().isEmpty());
        boolean isToListEmpty = (to == null || to.isEmpty());

        if (isFromAddressEmpty && isToListEmpty) {
            throw new IllegalArgumentException("Both 'fromAddress' and 'to' cannot be null or empty.");
        }
    }

    @PrePersist
    @PreUpdate
    private void validateBeforeSave() {
        validateEmailMessage();
    }
}
