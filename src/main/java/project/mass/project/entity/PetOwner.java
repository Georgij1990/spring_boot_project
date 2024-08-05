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

    @ManyToMany
    @JoinTable(
            name = "pet_pet_owner",
            joinColumns = @JoinColumn(name = "pet_owner_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id")
    )
    private List<Pet> pets;


}
