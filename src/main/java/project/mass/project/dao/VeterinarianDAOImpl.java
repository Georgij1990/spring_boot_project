package project.mass.project.dao;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.mass.project.entity.Veterinarian;

import java.util.List;

@Repository
public class VeterinarianDAOImpl implements VeterinarianDAO {

    private EntityManager em;

    public VeterinarianDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void saveVeterinarian(Veterinarian v) {
        this.em.persist(v);
    }

    @Override
    public Veterinarian getVeterinarian(int id) {
        return this.em.find(Veterinarian.class, id);
    }

    @Override
    @Transactional
    public void deleteVeterinarian(int id) {
        Veterinarian v = this.em.find(Veterinarian.class, id);
        this.em.remove(v);
    }

    @Override
    @Transactional
    public void updateVeterinarian(Veterinarian v) {
        this.em.merge(v);
    }

    @Override
    public List<Veterinarian> getAllVeterinarian() {
        return this.em.createQuery("from Veterinarian", Veterinarian.class).getResultList();
    }
}
