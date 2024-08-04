package project.mass.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "visit")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "visit_date")
    private LocalDate visitDate;

    @ManyToOne
    @JoinColumn(name = "pet_owner_id")
    private PetOwner petOwner;

    @ManyToOne
    @JoinColumn(name = "veterinarian_clinic_id")
    private VetClinic vetClinic;

    public Visit() {}

    public Visit(LocalDate visitDate, PetOwner petOwner, VetClinic vetClinic) {
        this.visitDate = visitDate;
        this.petOwner = petOwner;
        this.vetClinic = vetClinic;
    }

    public int getId() {
        return id;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public PetOwner getPetOwner() {
        return petOwner;
    }

    public void setPetOwner(PetOwner petOwner) {
        this.petOwner = petOwner;
    }

    public VetClinic getVetClinic() {
        return vetClinic;
    }

    public void setVetClinic(VetClinic vetClinic) {
        this.vetClinic = vetClinic;
    }
}
