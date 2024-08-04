package project.mass.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pet_owner")
public class PetOwner extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "subscription_type")
    private String subscriptionType;

    @OneToMany(mappedBy = "petOwner")
    private List<Visit> visits;

    public PetOwner() {}

    public PetOwner(LocalDate birthDate, String email, String firstName, String lastName, String phoneNumber, LocalDate registrationDate, String subscriptionType) {
        super(birthDate, email, firstName, lastName, phoneNumber);
        this.registrationDate = registrationDate;
        this.subscriptionType = subscriptionType;
    }

    public int getId() {
        return id;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }
}
