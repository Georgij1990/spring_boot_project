package project.mass.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "pet")
    private List<Referral> referrals;

    @OneToMany(mappedBy = "pet")
    private List<Vaccine> vaccines;

    @OneToMany(mappedBy = "pet")
    private List<VeterinarianProcedure> veterinarianProcedures;

    @ManyToMany
    @JoinTable(
            name = "pet_pet_owner",
            joinColumns = @JoinColumn(name = "pet_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_owner_id")
    )
    private List<PetOwner> petOwnerList;
}
