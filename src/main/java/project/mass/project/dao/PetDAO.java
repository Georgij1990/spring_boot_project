package project.mass.project.dao;

import project.mass.project.entity.Pet;

import java.util.List;

public interface PetDAO {
    void savePet(Pet pet);
    void deletePet(int petId);
    void updatePet(Pet pet);
    Pet getPet(int id);
    List<Pet> getPets();
}
