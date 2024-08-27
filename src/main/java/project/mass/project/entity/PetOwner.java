package project.mass.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import project.mass.project.Utility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pet_owner")
public class PetOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_type")
    private SubscriptionType subscriptionType;

    @NotNull
    @OneToMany(mappedBy = "petOwner")
    private List<Visit> visits = new ArrayList<>();

    @NotNull
    @OneToOne(mappedBy = "petOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Person person;


    @NotNull
    @ManyToMany
    @JoinTable(
            name = "pet_pet_owner",
            joinColumns = @JoinColumn(name = "pet_owner_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id")
    )
    private List<Pet> pets = new ArrayList<>();

    public PetOwner() {
    }

    public PetOwner(LocalDate registrationDate, SubscriptionType subscriptionType, List<Visit> visits, Person person, List<Pet> pets) {
        setRegistrationDate(registrationDate);
        setSubscriptionType(subscriptionType);
        setVisits(visits);
        setPerson(person);
        setPets(pets);
    }

    public int getId() {
        return id;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        if (registrationDate == null) {
            throw new IllegalArgumentException("registrationDate cannot be null");
        }
        this.registrationDate = registrationDate;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        if (subscriptionType == null) {
            throw new IllegalArgumentException("subscriptionType cannot be null");
        }
        this.subscriptionType = subscriptionType;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        if (Utility.hasNotNull(Collections.singletonList(visits))) {
            visits.stream().filter(Objects::nonNull).forEach(v -> this.visits.add(v));
        }
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("person cannot be null");
        }
        this.person = person;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        if (Utility.hasNotNull(Collections.singletonList(pets))) {
            pets.stream().filter(Objects::nonNull).forEach(p -> this.pets.add(p));
        }
    }
}
