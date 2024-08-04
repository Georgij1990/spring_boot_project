package project.mass.project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "veterinarian_clinic")
public class VetClinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "address")
    private String address;

    @Column(name = "clinic_name")
    private String clinicName;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "vetClinic")
    private List<Division> divisions;

    @OneToMany(mappedBy = "vetClinic")
    private List<Visit> visits;

    public VetClinic() {}

    public VetClinic(String clinicName, String address, String email) {
        this.clinicName = clinicName;
        this.address = address;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Division> getDivisions() {
        return divisions;
    }

    public void setDivisions(List<Division> divisions) {
        this.divisions = divisions;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }
}
