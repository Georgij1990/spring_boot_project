package project.mass.project.dao;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.mass.project.entity.EmailAttachment;

import java.util.List;

@Repository
public class EmailAttachmentDAOImpl implements EmailAttachmentDAO {

    private EntityManager em;

    public EmailAttachmentDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void saveEmailAttachment(EmailAttachment emailAttachment) {
        this.em.persist(emailAttachment);
    }

    @Override
    @Transactional
    public void deleteEmailAttachment(int emailAttachmentId) {
        EmailAttachment old = this.em.find(EmailAttachment.class, emailAttachmentId);
        this.em.remove(old);
    }

    @Override
    @Transactional
    public void updateEmailAttachment(EmailAttachment emailAttachment) {
        this.em.merge(emailAttachment);
    }

    @Override
    public EmailAttachment getEmailAttachment(int id) {
        return this.em.find(EmailAttachment.class, id);
    }

    @Override
    public List<EmailAttachment> getEmailAttachments() {
        return this.em.createQuery("from EmailAttachment", EmailAttachment.class).getResultList();
    }
}
