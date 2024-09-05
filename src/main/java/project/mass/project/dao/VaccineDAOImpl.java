package project.mass.project.dao;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.mass.project.entity.Vaccine;

import java.util.List;

@Repository
public class VaccineDAOImpl implements VaccineDAO {

    private EntityManager em;

    public VaccineDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void saveVaccine(Vaccine vaccine) {
        this.em.persist(vaccine);
    }

    @Override
    @Transactional
    public void deleteVaccine(int vaccineId) {
        Vaccine v = this.em.find(Vaccine.class, vaccineId);
        this.em.remove(v);
    }

    @Override
    @Transactional
    public void updateVaccine(Vaccine vaccine) {
        this.em.merge(vaccine);
    }

    @Override
    public Vaccine getVaccineById(int id) {
        return this.em.find(Vaccine.class, id);
    }

    @Override
    public List<Vaccine> getAllVaccines() {
        return this.em.createQuery("from Vaccine", Vaccine.class).getResultList();
    }
}
