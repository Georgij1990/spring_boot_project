package project.mass.project.dao;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.mass.project.entity.VetClinic;

import java.util.List;

@Repository
public class VetClinicDAOImpl implements VetClinicDAO {

    private EntityManager em;

    public VetClinicDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void saveVetClinic(VetClinic vetClinic) {
        this.em.persist(vetClinic);
    }

    @Override
    @Transactional
    public void updateVetClinic(VetClinic vetClinic) {
        this.em.merge(vetClinic);
    }

    @Override
    @Transactional
    public void deleteVetClinic(int vetClinicId) {
        VetClinic vetClinic = this.em.find(VetClinic.class, vetClinicId);
        this.em.remove(vetClinic);
    }

    @Override
    public VetClinic getVetClinic(int vetClinicId) {
        return this.em.find(VetClinic.class, vetClinicId);
    }

    @Override
    public List<VetClinic> getAllVetClinics() {
        return this.em.createQuery("from VetClinic", VetClinic.class).getResultList();
    }
}
