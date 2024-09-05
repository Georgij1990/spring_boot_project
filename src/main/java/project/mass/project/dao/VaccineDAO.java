package project.mass.project.dao;

import project.mass.project.entity.Vaccine;

import java.util.List;

public interface VaccineDAO {
    void saveVaccine(Vaccine vaccine);
    void deleteVaccine(int vaccineId);
    void updateVaccine(Vaccine vaccine);
    Vaccine getVaccineById(int id);
    List<Vaccine> getAllVaccines();
}
