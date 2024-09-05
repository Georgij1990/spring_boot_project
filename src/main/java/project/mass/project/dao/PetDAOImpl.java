package project.mass.project.dao;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.mass.project.entity.Pet;

import java.util.List;

@Repository
public class PetDAOImpl implements PetDAO {

    private EntityManager em;

    public PetDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void savePet(Pet pet) {
        this.em.persist(pet);
    }

    @Override
    @Transactional
    public void deletePet(int petId) {
        Pet petToDelete = this.em.find(Pet.class, petId);
        this.em.remove(petToDelete);
    }

    @Override
    @Transactional
    public void updatePet(Pet pet) {
        this.em.merge(pet);
    }

    @Override
    public Pet getPet(int id) {
        return this.em.find(Pet.class, id);
    }

    @Override
    public List<Pet> getPets() {
        return this.em.createQuery("select p from Pet p", Pet.class).getResultList();
    }
}
