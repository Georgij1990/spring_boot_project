package project.mass.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pet_owner")
public class PetOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_type")
    private SubscriptionType subscriptionType;

    @OneToMany(mappedBy = "petOwner")
    private List<Visit> visits;

    @OneToOne(mappedBy = "petOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Person person;


    @ManyToMany
    @JoinTable(
            name = "pet_pet_owner",
            joinColumns = @JoinColumn(name = "pet_owner_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id")
    )
    private List<Pet> pets;

    public PetOwner() {
    }

    public PetOwner(LocalDate registrationDate, SubscriptionType subscriptionType, List<Visit> visits, Person person, List<Pet> pets) {
        this.registrationDate = registrationDate;
        this.subscriptionType = subscriptionType;
        this.visits = visits;
        this.person = person;
        this.pets = pets;
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

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
