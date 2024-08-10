package project.mass.project.dao;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import project.mass.project.entity.Case;

import java.util.List;

@Repository
public class CaseDAOImpl implements CaseDAO {

    private EntityManager em;

    public CaseDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void createCase(Case c) {
        this.em.persist(c);
    }

    @Override
    public Case findCaseById(int id) {
        return this.em.find(Case.class, id);
    }

    @Override
    public List<Case> findCasesByCustomerSupportId(int id) {
        return this.em.createQuery("SELECT c FROM Case c WHERE c.customerSupport.id = :id", Case.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void updateCase(Case caseItem) {
        this.em.merge(caseItem);
    }

    @Override
    public void deleteCase(int id) {
        Case c = this.em.find(Case.class, id);
        this.em.remove(c);
    }
}
