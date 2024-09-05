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

    @NotNull
    @OneToMany(mappedBy = "vetClinic")
    private List<Division> divisions = new ArrayList<>();

    @NotNull
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
        return List.copyOf(this.divisions);
    }

    public void setDivisions(List<Division> divisions) {
        if (Utility.hasNotNull(Collections.singletonList(divisions))) {
            divisions.stream().filter(Objects::nonNull).forEach(d -> this.divisions.add(d));
        }
    }

    public void addDivision(Division division) {
        if (division == null) {
            throw new IllegalArgumentException("division that you want to add cannot be null");
        }
        this.divisions.add(division);
    }

    public void removeDivision(Division division) {
        if (division == null) {
            throw new IllegalArgumentException("division that you want to remove cannot be null");
        }
        this.divisions.remove(division);
    }

    public List<Visit> getVisits() {
        return List.copyOf(this.visits);
    }

    public void setVisits(List<Visit> visits) {
        if (Utility.hasNotNull(Collections.singletonList(visits))) {
            visits.stream().filter(Objects::nonNull).forEach(d -> this.visits.add(d));
        }
    }

    public void addVisit(Visit visit) {
        if (visit == null) {
            throw new IllegalArgumentException("visit that you want to add cannot be null");
        }
        this.visits.add(visit);
    }

    public void removeVisit(Visit visit) {
        if (visit == null) {
            throw new IllegalArgumentException("visit that you want to remove cannot be null");
        }
        this.visits.remove(visit);
    }

    @PrePersist
    @PreUpdate
    private void validatePetOwnerList() {
        if (this.divisions.isEmpty()) {
            throw new IllegalArgumentException("VetClinic must have at least one Division before being saved or updated.");
        }
    }
}
