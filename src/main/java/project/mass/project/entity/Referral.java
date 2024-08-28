package project.mass.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import project.mass.project.Utility;

import java.time.LocalDate;

@Entity
@Table(name = "referral")
public class Referral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "referral_number")
    private Long referralNumber;

    @NotNull
    @Column(name = "date_from")
    private LocalDate dateFrom;

    @NotNull
    @Column(name = "date_to")
    private LocalDate dateTo;

    @NotNull
    @NotEmpty
    @Column(name = "referral_name")
    private String referralName;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public Referral() {
    }

    public Referral(Long referralNumber, LocalDate dateFrom, LocalDate dateTo, String referralName, Pet pet) {
        setReferralNumber(referralNumber);
        setDateFrom(dateFrom);
        setDateTo(dateTo);
        setReferralName(referralName);
        setPet(pet);
    }

    public int getId() {
        return id;
    }

    public Long getReferralNumber() {
        return referralNumber;
    }

    public void setReferralNumber(Long referralNumber) {
        if (referralNumber == null) {
            throw new IllegalArgumentException("Referral Number cannot be null");
        } else if (referralNumber < 0) {
            throw new IllegalArgumentException("Referral Number cannot be negative");
        }
        this.referralNumber = referralNumber;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        if (dateFrom == null) {
            throw new IllegalArgumentException("Date From cannot be null");
        }
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        if (dateTo == null) {
            throw new IllegalArgumentException("Date To cannot be null");
        }
        this.dateTo = dateTo;
    }

    public String getReferralName() {
        return referralName;
    }

    public void setReferralName(String referralName) {
        if (!Utility.validateString(referralName)) {
            throw new IllegalArgumentException("Referral Name cannot be empty or null");
        }
        this.referralName = referralName;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("Pet cannot be null");
        }
        this.pet = pet;
    }
}
