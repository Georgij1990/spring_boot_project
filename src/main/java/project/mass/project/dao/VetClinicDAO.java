package project.mass.project.dao;

import project.mass.project.entity.VetClinic;

import java.util.List;

public interface VetClinicDAO {
    void saveVetClinic(VetClinic vetClinic);
    void updateVetClinic(VetClinic vetClinic);
    void deleteVetClinic(int vetClinicId);
    VetClinic getVetClinic(int vetClinicId);
    List<VetClinic> getAllVetClinics();
}
