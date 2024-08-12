package project.mass.project.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<CaseTask> findCaseTasksByCaseId(int id) {
        return this.em.createQuery("SELECT ct FROM CaseTask ct WHERE ct.caseItem.id = :id", CaseTask.class)
                .setParameter("id", id)
                .getResultList();
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
