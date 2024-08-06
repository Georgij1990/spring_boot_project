package project.mass.project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "email_message")
public class EmailMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ElementCollection
    @CollectionTable(name = "to_adresses", joinColumns = @JoinColumn(name = "email_message_id"))
    @Column(name = "to_address")
    private List<String> to;

    @ElementCollection
    @CollectionTable(name = "cc_adresses", joinColumns = @JoinColumn(name = "email_message_id"))
    @Column(name = "cc_address")
    private List<String> cc;

    @Column(name = "subject")
    private String subject;

    @Column(name = "email_message")
    private String message;

    @ManyToMany
    @JoinTable(
            name = "case_email_message",
            joinColumns = @JoinColumn(name = "email_message_id"),
            inverseJoinColumns = @JoinColumn(name = "case_id")
    )
    private List<Case> cases;

    @ManyToMany
    @JoinTable(
            name = "email_message_email_attachment",
            joinColumns = @JoinColumn(name = "email_message_id"),
            inverseJoinColumns = @JoinColumn(name = "email_attachment_id")
    )
    private List<EmailAttachment> emailAttachments;
}
