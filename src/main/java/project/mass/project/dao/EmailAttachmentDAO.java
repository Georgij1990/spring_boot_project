package project.mass.project.dao;

import project.mass.project.entity.EmailAttachment;

import java.util.List;

public interface EmailAttachmentDAO {
    void saveEmailAttachment(EmailAttachment emailAttachment);
    void deleteEmailAttachment(int emailAttachmentId);
    void updateEmailAttachment(EmailAttachment emailAttachment);
    EmailAttachment getEmailAttachment(int id);
    List<EmailAttachment> getEmailAttachments();
}
