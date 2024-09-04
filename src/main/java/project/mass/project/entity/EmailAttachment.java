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
@Table(name = "email_attachment")
public class EmailAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @NotEmpty
    @Column(name = "attachment_name")
    private String attachmentName;

    @NotNull
    @NotEmpty
    @Column(name = "content")
    private String content;

    @NotNull
    @ManyToMany
    @JoinTable(
            name = "email_message_email_attachment",
            joinColumns = @JoinColumn(name = "email_attachment_id"),
            inverseJoinColumns = @JoinColumn(name = "email_message_id")
    )
    private List<EmailMessage> emailMessages = new ArrayList<EmailMessage>();

    public EmailAttachment() {
    }

    public EmailAttachment(String attachmentName, String content, List<EmailMessage> emailMessages) {
        setAttachmentName(attachmentName);
        setContent(content);
        setEmailMessages(emailMessages);
    }

    public int getId() {
        return id;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        if (!Utility.validateString(attachmentName)) {
            throw new IllegalArgumentException("Attachment name cannot be null or empty");
        }
        this.attachmentName = attachmentName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (!Utility.validateString(content)) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }
        this.content = content;
    }

    public List<EmailMessage> getEmailMessages() {
        return List.copyOf(this.emailMessages);
    }

    public void setEmailMessages(List<EmailMessage> emailMessages) {
        if (Utility.hasNotNull(Collections.singletonList(emailMessages))) {
            emailMessages.stream().filter(Objects::nonNull).forEach(eM -> this.emailMessages.add(eM));
        }
    }

    public void addEmailMessage(EmailMessage emailMessage) {
        if (emailMessage == null) {
            throw new IllegalArgumentException("Email message that you want to add cannot be null");
        }
        this.emailMessages.add(emailMessage);
    }

    public void removeEmailMessage(EmailMessage emailMessage) {
        if (emailMessage == null) {
            throw new IllegalArgumentException("Email message that you want to remove cannot be null");
        }
        this.emailMessages.remove(emailMessage);
    }
}
