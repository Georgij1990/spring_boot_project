package project.mass.project.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.mass.project.entity.Case;
import project.mass.project.entity.CaseTask;

import java.util.List;

@Repository
public class CaseTaskDAOImpl implements CaseTaskDAO {

    private EntityManager em;

    @Autowired
    public CaseTaskDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void saveCaseTask(CaseTask caseTask) {
        this.em.persist(caseTask);
    }

    @Override
    public CaseTask findCaseTaskById(int id) {
        return this.em.find(CaseTask.class, id);
    }

    @Override
    public Page<CaseTask> findCaseTasksByCaseId(int id, Pageable pageable) {
        List<CaseTask> caseTaskList = this.em.createQuery("SELECT ct FROM CaseTask ct WHERE ct.caseItem.id = :id", CaseTask.class)
                .setParameter("id", id)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        long total = em.createQuery("SELECT COUNT(c) FROM CaseTask c WHERE c.caseItem.id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();

        return new PageImpl<>(caseTaskList, pageable, total);
    }

    @Override
    @Transactional
    public void updateCaseTask(CaseTask caseTask) {
        this.em.merge(caseTask);
    }

    @Override
    @Transactional
    public void deleteCaseTask(int id) {
        CaseTask ct = findCaseTaskById(id);
        this.em.remove(ct);
    }
}
