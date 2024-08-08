package project.mass.project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "vaterinarian")
public class Veterinarian extends Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

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

    public Veterinarian(String veterinarianSpecialization, List<String> qualifications, List<VeterinarianProcedure> veterinarianProcedures, Division division) {
        this.veterinarianSpecialization = veterinarianSpecialization;
        this.qualifications = qualifications;
        this.veterinarianProcedures = veterinarianProcedures;
        this.division = division;
    }

    public int getId() {
        return id;
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
