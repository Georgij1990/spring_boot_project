package project.mass.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import project.mass.project.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "veterinarian_clinic")
public class VetClinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @NotEmpty
    @Column(name = "address")
    private String address;

    @NotNull
    @NotEmpty
    @Column(name = "clinic_name")
    private String clinicName;

    @NotNull
    @NotEmpty
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "vetClinic")
    private List<Division> divisions = new ArrayList<>();

    @OneToMany(mappedBy = "vetClinic")
    private List<Visit> visits = new ArrayList<>();

    public VetClinic() {}

    public VetClinic(String clinicName, String address, String email) {
        setClinicName(clinicName);
        setAddress(address);
        setEmail(email);
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (!Utility.validateString(address)) {
            throw new IllegalArgumentException("address cannot be null or empty");
        }
        this.address = address;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        if (!Utility.validateString(clinicName)) {
            throw new IllegalArgumentException("clinicName cannot be null or empty");
        }
        this.clinicName = clinicName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!Utility.validateString(email)) {
            throw new IllegalArgumentException("email cannot be null or empty");
        }
        this.email = email;
    }

    public List<Division> getDivisions() {
        return divisions;
    }

    public void setDivisions(List<Division> divisions) {
        if (Utility.hasNotNull(Collections.singletonList(divisions))) {
            divisions.stream().filter(Objects::nonNull).forEach(d -> this.divisions.add(d));
        }
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        if (Utility.hasNotNull(Collections.singletonList(visits))) {
            visits.stream().filter(Objects::nonNull).forEach(d -> this.visits.add(d));
        }
    }
}
