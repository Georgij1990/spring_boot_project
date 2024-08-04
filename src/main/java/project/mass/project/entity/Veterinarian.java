package project.mass.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "vaterinarian")
public class Veterinarian extends Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "veterinarian_specialization")
    private String veterinarianSpecialization;

    public Veterinarian() {}

    public Veterinarian(LocalDate birthDate, String email, String firstName, String lastName, String phoneNumber, LocalDate hireDate, String contactType, Double salary, String veterinarianSpecialization) {
        super(birthDate, email, firstName, lastName, phoneNumber, hireDate, contactType, salary);
        this.veterinarianSpecialization = veterinarianSpecialization;
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
}
