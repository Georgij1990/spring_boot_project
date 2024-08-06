package project.mass.project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "email_attachment")
public class EmailAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "attachment_name")
    private String attachmentName;

    @Column(name = "content")
    private String content;

    @ManyToMany
    @JoinTable(
            name = "email_message_email_attachment",
            joinColumns = @JoinColumn(name = "email_attachment_id"),
            inverseJoinColumns = @JoinColumn(name = "email_message_id")
    )
    private List<EmailMessage> emailMessages;
}
