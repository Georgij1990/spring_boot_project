package project.mass.project.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.mass.project.entity.CustomerSupport;
import project.mass.project.entity.Person;
import project.mass.project.entity.PetOwner;
import project.mass.project.entity.Veterinarian;

import java.util.List;

@Repository
public class PersonDAOImpl implements PersonDAO {

    private EntityManager em;

    @Autowired
    public PersonDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void savePerson(Person person) {
        this.em.persist(person);
    }

    @Override
    @Transactional
    public void savePetOwner(Person person, PetOwner petOwner) {
        person.setPetOwner(petOwner);
        this.em.persist(person);
    }

    @Override
    @Transactional
    public void saveVeterinarian(Person person, Veterinarian veterinarian) {
        person.setVeterinarian(veterinarian);
        this.em.persist(person);
    }

    @Override
    @Transactional
    public void saveCustomerSupport(Person person, CustomerSupport customerSupport) {
        this.em.persist(customerSupport);
        person.setCustomerSupport(customerSupport);
        this.em.persist(person);
    }

    @Override
    public Person findPersonById(int id) {
        return this.em.find(Person.class, id);
    }

    @Override
    @Transactional
    public void deletePersonById(int id) {
        Person person = this.em.find(Person.class, id);
        this.em.remove(person);
    }

    @Override
    @Transactional
    public void updatePerson(Person person) {
        this.em.merge(person);
    }

    @Override
    public PetOwner findPetOwnerById(int id) {
        return this.em.find(PetOwner.class, id);
    }

    @Override
    @Transactional
    public void deletePetOwnerById(int id) {
        PetOwner petOwner = this.em.find(PetOwner.class, id);
        this.em.remove(petOwner);
    }

    @Override
    @Transactional
    public void updatePetOwner(PetOwner petOwner) {
        this.em.merge(petOwner);
    }

    @Override
    public Veterinarian findVeterinarianById(int id) {
        return this.em.find(Veterinarian.class, id);
    }

    @Override
    @Transactional
    public void deleteVeterinarianById(int id) {
        Veterinarian veterinarian = this.em.find(Veterinarian.class, id);
        this.em.remove(veterinarian);
    }

    @Override
    @Transactional
    public void updateVeterinarian(Veterinarian veterinarian) {
        this.em.merge(veterinarian);
    }

    @Override
    public CustomerSupport findCustomerSupportById(int id) {
        return this.em.find(CustomerSupport.class, id);
    }

    @Override
    public List<CustomerSupport> findAllCustomerSupports() {
        return this.em.createQuery("SELECT c FROM CustomerSupport c", CustomerSupport.class).getResultList();
    }

    @Override
    @Transactional
    public void deleteCustomerSupportById(int id) {
        CustomerSupport customerSupport = this.em.find(CustomerSupport.class, id);
        this.em.remove(customerSupport);
    }

    @Override
    @Transactional
    public void updateCustomerSupport(CustomerSupport customerSupport) {
        this.em.merge(customerSupport);
    }
}
