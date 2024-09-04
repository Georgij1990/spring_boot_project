package project.mass.project.dao;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.mass.project.entity.Division;

import java.util.List;

@Repository
public class DivisionDAOImpl implements DivisionDAO {

    private EntityManager em;

    public DivisionDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void saveDivision(Division division) {
        this.em.persist(division);
    }

    @Override
    @Transactional
    public void updateDivision(Division division) {
        this.em.merge(division);
    }

    @Override
    @Transactional
    public void deleteDivision(Division division) {
        Division div = this.em.find(Division.class, division.getId());
        this.em.remove(div);
    }

    @Override
    public Division getDivision(int id) {
        return this.em.find(Division.class, id);
    }

    @Override
    public List<Division> getDivisions() {
        return this.em.createQuery("select d from Division d", Division.class).getResultList();
    }
}
