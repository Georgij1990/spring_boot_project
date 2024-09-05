package project.mass.project.dao;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.mass.project.entity.Referral;

import java.util.List;

@Repository
public class ReferralDAOImpl implements ReferralDAO {

    private EntityManager em;

    public ReferralDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void saveReferral(Referral ref) {
        this.em.persist(ref);
    }

    @Override
    @Transactional
    public void deleteReferral(int referralId) {
        Referral referral = this.em.find(Referral.class, referralId);
        this.em.remove(referral);
    }

    @Override
    @Transactional
    public void updateReferral(Referral ref) {
        this.em.merge(ref);
    }

    @Override
    public Referral getReferral(int id) {
        return this.em.find(Referral.class, id);
    }

    @Override
    public List<Referral> getReferrals() {
        return this.em.createQuery("from Referral", Referral.class).getResultList();
    }
}
