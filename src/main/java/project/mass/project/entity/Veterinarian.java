package project.mass.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import project.mass.project.Utility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "veterinarian")
public class Veterinarian extends Employee {

    @NotNull
    @NotEmpty
    @Column(name = "veterinarian_specialization")
    private String veterinarianSpecialization;

    @NotNull
    @ElementCollection
    @CollectionTable(name = "qualifications", joinColumns = @JoinColumn(name = "veterinarian_id"))
    @Column(name = "qualification")
    private List<String> qualifications = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "veterinarian")
    private List<VeterinarianProcedure> veterinarianProcedures = new ArrayList<>();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "division_id")
    private Division division;

    public Veterinarian() {
    }

    public Veterinarian(LocalDate hireDate, ContractType contractType, Double salary, Person person, String veterinarianSpecialization, Division division) {
        super(hireDate, contractType, salary, person);
        setVeterinarianSpecialization(veterinarianSpecialization);
        setDivision(division);
        setQualifications(qualifications);
        setVeterinarianProcedures(veterinarianProcedures);
    }

    public String getVeterinarianSpecialization() {
        return veterinarianSpecialization;
    }

    public void setVeterinarianSpecialization(String veterinarianSpecialization) {
        if (!Utility.validateString(veterinarianSpecialization)) {
            throw new IllegalArgumentException("Veterinarian specialization cannot be null or empty");
        }
        this.veterinarianSpecialization = veterinarianSpecialization;
    }

    public List<String> getQualifications() {
        return List.copyOf(this.qualifications);
    }

    public void setQualifications(List<String> qualifications) {
        if (Utility.hasNotNull(Collections.singletonList(qualifications))) {
            qualifications.stream().filter(Objects::nonNull).filter(q -> !q.isBlank()).forEach(q -> {
                if (!this.qualifications.contains(q)) {
                    this.qualifications.add(q);
                }
            });
        }
    }

    public void addQualification(String qualification) {
        if (!Utility.validateString(qualification)) {
            throw new IllegalArgumentException("Qualification that you want to add cannot be null or empty");
        } else if (this.qualifications.contains(qualification)) {
            throw new IllegalArgumentException("Qualification record already exists.");
        }
        this.qualifications.add(qualification);
    }

    public void removeQualification(String qualification) {
        if (!Utility.validateString(qualification)) {
            throw new IllegalArgumentException("Qualification that you want to remove cannot be null or empty");
        }
        this.qualifications.remove(qualification);
    }

    public List<VeterinarianProcedure> getVeterinarianProcedures() {
        return List.copyOf(this.veterinarianProcedures);
    }

    public void setVeterinarianProcedures(List<VeterinarianProcedure> veterinarianProcedures) {
        if (Utility.hasNotNull(Collections.singletonList(veterinarianProcedures))) {
            veterinarianProcedures.stream().filter(Objects::nonNull).forEach(vP -> this.veterinarianProcedures.add(vP));
        }
    }

    public void addVeterinarianProcedure(VeterinarianProcedure vP) {
        if (vP == null) {
            throw new IllegalArgumentException("Veterinarian procedure that you want to add cannot be null");
        }
        this.veterinarianProcedures.add(vP);
    }

    public void removeVeterinarianProcedure(VeterinarianProcedure vP) {
        if (vP == null) {
            throw new IllegalArgumentException("Veterinarian procedure that you want to remove cannot be null");
        }
        this.veterinarianProcedures.remove(vP);
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        if (division == null) {
            throw new IllegalArgumentException("Division cannot be null");
        }
        this.division = division;
    }
}
