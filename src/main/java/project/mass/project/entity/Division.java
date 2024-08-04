package project.mass.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "division")
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "division_name")
    private String divisionName;

    @ManyToOne
    @JoinColumn(name = "veterinarian_clinic_id")
    private VetClinic vetClinic;

    public Division() {}

    public Division(String divisionName, VetClinic vetClinic) {
        this.divisionName = divisionName;
        this.vetClinic = vetClinic;
    }

    public int getId() {
        return id;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public VetClinic getVetClinic() {
        return vetClinic;
    }

    public void setVetClinic(VetClinic vetClinic) {
        this.vetClinic = vetClinic;
    }
}
