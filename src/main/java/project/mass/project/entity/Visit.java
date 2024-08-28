package project.mass.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "visit")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "visit_date")
    private LocalDate visitDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pet_owner_id")
    private PetOwner petOwner;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "veterinarian_clinic_id")
    private VetClinic vetClinic;

    public Visit() {}

    public Visit(LocalDate visitDate, PetOwner petOwner, VetClinic vetClinic) {
        setVisitDate(visitDate);
        setPetOwner(petOwner);
        setVetClinic(vetClinic);
    }

    public int getId() {
        return id;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        if (visitDate == null) {
            throw new IllegalArgumentException("VisitDate cannot be null");
        }
        this.visitDate = visitDate;
    }

    public PetOwner getPetOwner() {
        return petOwner;
    }

    public void setPetOwner(PetOwner petOwner) {
        if (petOwner == null) {
            throw new IllegalArgumentException("PetOwner cannot be null");
        }
        this.petOwner = petOwner;
    }

    public VetClinic getVetClinic() {
        return vetClinic;
    }

    public void setVetClinic(VetClinic vetClinic) {
        if (vetClinic == null) {
            throw new IllegalArgumentException("VetClinic cannot be null");
        }
        this.vetClinic = vetClinic;
    }
}
