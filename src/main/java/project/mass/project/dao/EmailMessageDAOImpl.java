package project.mass.project.dao;

import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.mass.project.entity.EmailMessage;

import java.util.List;

@Repository
public class EmailMessageDAOImpl implements EmailMessageDAO {

    private EntityManager em;

    public EmailMessageDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void saveEmailMessage(EmailMessage emailMessage) {
        this.em.persist(emailMessage);
    }

    @Override
    public EmailMessage getEmailMessage(int id) {
        return this.em.find(EmailMessage.class, id);
    }

    @Override
    @Transactional
    public void deleteEmailMessage(int id) {
        EmailMessage emailMessage = this.em.find(EmailMessage.class, id);
        this.em.remove(emailMessage);
    }

    @Override
    @Transactional
    public void updateEmailMessage(EmailMessage emailMessage) {
        this.em.merge(emailMessage);
    }

    @Override
    public List<EmailMessage> getAllEmailMessages() {
        return this.em.createQuery("from EmailMessage", EmailMessage.class).getResultList();
    }
}
