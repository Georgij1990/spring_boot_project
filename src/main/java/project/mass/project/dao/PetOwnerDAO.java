package project.mass.project.dao;

import project.mass.project.entity.PetOwner;

import java.util.List;

public interface PetOwnerDAO {
    void savePetOwner(PetOwner petOwner);
    PetOwner getPetOwner(int petOwnerId);
    List<PetOwner> getAllPetOwners();
    void deletePetOwner(int petOwnerId);
    void updatePetOwner(PetOwner petOwner);
}
