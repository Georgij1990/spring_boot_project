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
        return veterinarians;
    }

    public void setVeterinarians(List<Veterinarian> veterinarians) {
        if (Utility.hasNotNull(Collections.singletonList(veterinarians))) {
            veterinarians.stream().filter(Objects::nonNull).forEach(v -> this.veterinarians.add(v));
        } else {
            throw new IllegalArgumentException("Veterinarians cannot be null or empty");
        }
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
}
