package project.mass.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import project.mass.project.Utility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @NotEmpty
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @NotNull
    @OneToMany(mappedBy = "pet")
    private List<Referral> referrals = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "pet")
    private List<Vaccine> vaccines = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "pet")
    private List<VeterinarianProcedure> veterinarianProcedures = new ArrayList<>();

    @NotNull
    @NotEmpty
    @ManyToMany
    @JoinTable(
            name = "pet_pet_owner",
            joinColumns = @JoinColumn(name = "pet_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_owner_id")
    )
    private List<PetOwner> petOwnerList = new ArrayList<>();

    public Pet() {
    }

    public Pet(String name, LocalDate dateOfBirth, List<Referral> referrals, List<Vaccine> vaccines, List<VeterinarianProcedure> veterinarianProcedures, List<PetOwner> petOwnerList) {
        setName(name);
        setDateOfBirth(dateOfBirth);
        setReferrals(referrals);
        setVaccines(vaccines);
        setVeterinarianProcedures(veterinarianProcedures);
        setPetOwnerList(petOwnerList);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!Utility.validateString(name)) {
            throw new IllegalArgumentException("Pet name cannot be null or empty");
        }
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth cannot be null");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public List<Referral> getReferrals() {
        return referrals;
    }

    public void setReferrals(List<Referral> referrals) {
        if (Utility.hasNotNull(Collections.singletonList(referrals))) {
            referrals.stream().filter(Objects::nonNull).forEach(r -> this.referrals.add(r));
        }
    }

    public List<Vaccine> getVaccines() {
        return vaccines;
    }

    public void setVaccines(List<Vaccine> vaccines) {
        if (Utility.hasNotNull(Collections.singletonList(vaccines))) {
            vaccines.stream().filter(Objects::nonNull).forEach(v -> this.vaccines.add(v));
        }
    }

    public List<VeterinarianProcedure> getVeterinarianProcedures() {
        return veterinarianProcedures;
    }

    public void setVeterinarianProcedures(List<VeterinarianProcedure> veterinarianProcedures) {
        if (Utility.hasNotNull(Collections.singletonList(veterinarianProcedures))) {
            veterinarianProcedures.stream().filter(Objects::nonNull).forEach(vP -> this.veterinarianProcedures.add(vP));
        }
    }

    public List<PetOwner> getPetOwnerList() {
        return petOwnerList;
    }

    public void setPetOwnerList(List<PetOwner> petOwnerList) {
        if (Utility.hasNotNull(Collections.singletonList(petOwnerList))) {
            petOwnerList.stream().filter(Objects::nonNull).forEach(pO -> this.petOwnerList.add(pO));
        }
    }
}
