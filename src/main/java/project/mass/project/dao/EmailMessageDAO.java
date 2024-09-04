package project.mass.project.dao;

import project.mass.project.entity.EmailMessage;

import java.util.List;

public interface EmailMessageDAO {
    void saveEmailMessage(EmailMessage emailMessage);
    EmailMessage getEmailMessage(int id);
    void deleteEmailMessage(int id);
    void updateEmailMessage(EmailMessage emailMessage);
    List<EmailMessage> getAllEmailMessages();
}
