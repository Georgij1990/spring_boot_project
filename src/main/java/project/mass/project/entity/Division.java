package project.mass.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import project.mass.project.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "division")
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "division_name")
    private String divisionName;

    @NotEmpty
    @NotNull
    @OneToMany(mappedBy = "division")
    private List<Veterinarian> veterinarians = new ArrayList<>();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "veterinarian_clinic_id")
    private VetClinic vetClinic;

    public Division() {
    }

    public Division(String divisionName, List<Veterinarian> veterinarians, VetClinic vetClinic) {
        setDivisionName(divisionName);
        setVeterinarians(veterinarians);
        setVetClinic(vetClinic);
    }

    public int getId() {
        return id;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        if (!Utility.validateString(divisionName)) {
            throw new IllegalArgumentException("Division name cannot be null or empty");
        }
        this.divisionName = divisionName;
    }

    public List<Veterinarian> getVeterinarians() {
        return List.copyOf(this.veterinarians);
    }

    public void setVeterinarians(List<Veterinarian> veterinarians) {
        if (Utility.hasNotNull(Collections.singletonList(veterinarians))) {
            veterinarians.stream().filter(Objects::nonNull).forEach(v -> this.veterinarians.add(v));
        }
    }

    public void addVeterinarian(Veterinarian veterinarian) {
        if (veterinarians == null) {
            throw new IllegalArgumentException("Veterinarian that you want to add cannot be null");
        }
        this.veterinarians.add(veterinarian);
    }

    public void deleteVeterinarian(Veterinarian veterinarian) {
        if (veterinarians == null) {
            throw new IllegalArgumentException("Veterinarian that you want to delete cannot be null");
        }
        this.veterinarians.remove(veterinarian);
    }

    public VetClinic getVetClinic() {
        return vetClinic;
    }

    public void setVetClinic(VetClinic vetClinic) {
        if (vetClinic == null) {
            throw new IllegalArgumentException("Vet clinic cannot be null");
        }
        this.vetClinic = vetClinic;
    }

    @PrePersist
    @PreUpdate
    private void validatePetOwnerList() {
        if (this.veterinarians.isEmpty()) {
            throw new IllegalArgumentException("Division must have at least one Veterinarian before being saved or updated.");
        }
    }
}
