package project.mass.project.dao;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.mass.project.entity.Visit;

import java.util.List;

@Repository
public class VisitDAOImpl implements VisitDAO {

    private EntityManager em;

    public VisitDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void saveVisit(Visit visit) {
        this.em.persist(visit);
    }

    @Override
    @Transactional
    public void deleteVisit(int visitId) {
        Visit visit = this.em.find(Visit.class, visitId);
        this.em.remove(visit);
    }

    @Override
    @Transactional
    public void updateVisit(Visit visit) {
        this.em.merge(visit);
    }

    @Override
    public Visit getVisit(int visitId) {
        return this.em.find(Visit.class, visitId);
    }

    @Override
    public List<Visit> getVisits() {
        return this.em.createQuery("select v from Visit v", Visit.class).getResultList();
    }
}
