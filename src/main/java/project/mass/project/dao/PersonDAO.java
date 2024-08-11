package project.mass.project.dao;

import project.mass.project.entity.CustomerSupport;
import project.mass.project.entity.Person;
import project.mass.project.entity.PetOwner;
import project.mass.project.entity.Veterinarian;

import java.util.List;

public interface PersonDAO {

    void savePerson(Person person);
    void savePetOwner(Person person, PetOwner petOwner);
    void saveVeterinarian(Person person, Veterinarian veterinarian);
    void saveCustomerSupport(Person person, CustomerSupport customerSupport);

    Person findPersonById(int id);
    void deletePersonById(int id);
    void updatePerson(Person person);

    PetOwner findPetOwnerById(int id);
    void deletePetOwnerById(int id);
    void updatePetOwner(PetOwner petOwner);

    Veterinarian findVeterinarianById(int id);
    void deleteVeterinarianById(int id);
    void updateVeterinarian(Veterinarian veterinarian);

    CustomerSupport findCustomerSupportById(int id);
    List<CustomerSupport> findAllCustomerSupports();
    void deleteCustomerSupportById(int id);
    void updateCustomerSupport(CustomerSupport customerSupport);
}
