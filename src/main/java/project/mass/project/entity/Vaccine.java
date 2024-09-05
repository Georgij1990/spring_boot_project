package project.mass.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import project.mass.project.Utility;

@Entity
@Table(name = "vaccine")
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @NotEmpty
    @Column(name = "name")
    private String name;

    @NotNull
    @NotEmpty
    @Column(name = "disease")
    private String disease;

    @NotNull
    @NotEmpty
    @Column(name = "serial_number")
    private String serialNumber;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public Vaccine() {
    }

    public Vaccine(String name, String disease, String serialNumber, Pet pet) {
        setName(name);
        setDisease(disease);
        setSerialNumber(serialNumber);
        setPet(pet);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!Utility.validateString(name)) {
            throw new IllegalArgumentException("Vaccine name cannot be empty");
        }
        this.name = name;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        if (!Utility.validateString(disease)) {
            throw new IllegalArgumentException("Vaccine disease cannot be empty");
        }
        this.disease = disease;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        if (!Utility.validateString(serialNumber)) {
            throw new IllegalArgumentException("Vaccine serial number cannot be empty");
        }
        this.serialNumber = serialNumber;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("Vaccine pet cannot be null");
        }
        this.pet = pet;
    }
}
