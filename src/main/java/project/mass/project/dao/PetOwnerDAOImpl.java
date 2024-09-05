package project.mass.project.dao;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.mass.project.entity.PetOwner;

import java.util.List;

@Repository
public class PetOwnerDAOImpl implements PetOwnerDAO {

    private EntityManager em;

    public PetOwnerDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void savePetOwner(PetOwner petOwner) {
        this.em.persist(petOwner);
    }

    @Override
    public PetOwner getPetOwner(int petOwnerId) {
        return this.em.find(PetOwner.class, petOwnerId);
    }

    @Override
    public List<PetOwner> getAllPetOwners() {
        return this.em.createQuery("select p from PetOwner p", PetOwner.class).getResultList();
    }

    @Override
    @Transactional
    public void deletePetOwner(int petOwnerId) {
        PetOwner petOwner = this.em.find(PetOwner.class, petOwnerId);
        this.em.remove(petOwner);
    }

    @Override
    @Transactional
    public void updatePetOwner(PetOwner petOwner) {
        this.em.merge(petOwner);
    }
}
