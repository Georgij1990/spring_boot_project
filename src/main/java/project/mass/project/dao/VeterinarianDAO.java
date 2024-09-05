package project.mass.project.dao;

import project.mass.project.entity.Veterinarian;

import java.util.List;

public interface VeterinarianDAO {
    void saveVeterinarian(Veterinarian v);
    Veterinarian getVeterinarian(int id);
    void deleteVeterinarian(int id);
    void updateVeterinarian(Veterinarian v);
    List<Veterinarian> getAllVeterinarian();
}
