package project.mass.project.dao;

import jakarta.persistence.EntityManager;
import project.mass.project.entity.Case;
import project.mass.project.entity.CaseTask;

import java.util.List;

public class CaseTaskDAOImpl implements CaseTaskDAO {

    private EntityManager em;

    public CaseTaskDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void createCaseTask(CaseTask caseTask, Case caseItem) {
        caseTask.setCaseItem(caseItem);
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
    public void updateCaseTask(CaseTask caseTask) {
        this.em.merge(caseTask);
    }

    @Override
    public void deleteCaseTask(int id) {
        CaseTask ct = findCaseTaskById(id);
        this.em.remove(ct);
    }
}
