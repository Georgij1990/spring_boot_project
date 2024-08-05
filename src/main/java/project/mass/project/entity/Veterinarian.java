package project.mass.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vaterinarian")
public class Veterinarian extends Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "veterinarian_specialization")
    private String veterinarianSpecialization;

    @ElementCollection
    @CollectionTable(name = "qualifications", joinColumns = @JoinColumn(name = "veterinarian_id"))
    @Column(name = "qualification")
    private List<String> qualifications;

    @OneToMany(mappedBy = "veterinarian")
    private List<VeterinarianProcedure> veterinarianProcedures;

    @ManyToOne
    @JoinColumn(name = "division_id")
    private Division division;
}
