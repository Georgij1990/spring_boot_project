package project.mass.project.dao;

import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.mass.project.entity.Case;

import java.util.List;

@Repository
public class CaseDAOImpl implements CaseDAO {

    private EntityManager em;

    public CaseDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void saveCase(Case c) {
        this.em.persist(c);
    }

    @Override
    public Case findCaseById(int id) {
        return this.em.find(Case.class, id);
    }

    @Override
    public Page<Case> findCasesByCustomerSupportId(int id, Pageable pageable) {
        List<Case> cases = em.createQuery("SELECT c FROM Case c WHERE c.customerSupport.id = :id", Case.class)
                .setParameter("id", id)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        long total = em.createQuery("SELECT COUNT(c) FROM Case c WHERE c.customerSupport.id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();

        return new PageImpl<>(cases, pageable, total);
    }

    @Override
    public List<Case> findAllCaseItems() {
        return this.em.createQuery("SELECT c FROM Case c", Case.class).getResultList();
    }

    @Override
    @Transactional
    public void updateCase(@Valid Case caseItem) {
        this.em.merge(caseItem);
    }

    @Override
    @Transactional
    public void deleteCase(int id) {
        Case c = this.em.find(Case.class, id);
        this.em.remove(c);
    }
}
