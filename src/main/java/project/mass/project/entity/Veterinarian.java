package project.mass.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "veterinarian")
public class Veterinarian extends Employee {

    @Column(name = "veterinarian_specialization")
    private String veterinarianSpecialization;

    @ElementCollection
    @CollectionTable(name = "qualifications", joinColumns = @JoinColumn(name = "veterinarian_id"))
    @Column(name = "qualification")
    private List<String> qualifications;

    @OneToMany(mappedBy = "veterinarian")
    private List<VeterinarianProcedure> veterinarianProcedures;

    @ManyToOne
    @JoinColumn(name = "division_id")
    private Division division;

    public Veterinarian() {
    }

    public Veterinarian(LocalDate hireDate, String contractType, Double salary, Person person, String veterinarianSpecialization, Division division) {
        super(hireDate, contractType, salary, person);
        this.veterinarianSpecialization = veterinarianSpecialization;
        this.division = division;
        this.qualifications = new ArrayList<>();
        this.veterinarianProcedures = new ArrayList<>();
    }

    public String getVeterinarianSpecialization() {
        return veterinarianSpecialization;
    }

    public void setVeterinarianSpecialization(String veterinarianSpecialization) {
        this.veterinarianSpecialization = veterinarianSpecialization;
    }

    public List<String> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<String> qualifications) {
        this.qualifications = qualifications;
    }

    public List<VeterinarianProcedure> getVeterinarianProcedures() {
        return veterinarianProcedures;
    }

    public void setVeterinarianProcedures(List<VeterinarianProcedure> veterinarianProcedures) {
        this.veterinarianProcedures = veterinarianProcedures;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }
}
