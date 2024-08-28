package project.mass.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import project.mass.project.Utility;

import java.time.LocalDate;

@Entity
@Table(name = "veterinarian_procedure")
public class VeterinarianProcedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "date_of_procedure")
    private LocalDate dateOfProcedure;

    @NotNull
    @NotEmpty
    @Column(name = "name_of_procedure")
    private String nameOfProcedure;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "veterinarian_id")
    private Veterinarian veterinarian;

    public VeterinarianProcedure() {
    }

    public VeterinarianProcedure(LocalDate dateOfProcedure, String nameOfProcedure, Pet pet, Veterinarian veterinarian) {
        setDateOfProcedure(dateOfProcedure);
        setNameOfProcedure(nameOfProcedure);
        setPet(pet);
        setVeterinarian(veterinarian);
    }

    public int getId() {
        return id;
    }

    public LocalDate getDateOfProcedure() {
        return dateOfProcedure;
    }

    public void setDateOfProcedure(LocalDate dateOfProcedure) {
        if (dateOfProcedure == null) {
            throw new IllegalArgumentException("The date of procedure cannot be null");
        }
        this.dateOfProcedure = dateOfProcedure;
    }

    public String getNameOfProcedure() {
        return nameOfProcedure;
    }

    public void setNameOfProcedure(String nameOfProcedure) {
        if (!Utility.validateString(nameOfProcedure)) {
            throw new IllegalArgumentException("The name of procedure cannot be null or empty");
        }
        this.nameOfProcedure = nameOfProcedure;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("The pet cannot be null");
        }
        this.pet = pet;
    }

    public Veterinarian getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(Veterinarian veterinarian) {
        if (veterinarian == null) {
            throw new IllegalArgumentException("The veterinarian cannot be null");
        }
        this.veterinarian = veterinarian;
    }
}
