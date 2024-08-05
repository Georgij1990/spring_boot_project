package project.mass.project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "division")
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "division_name")
    private String divisionName;

    @OneToMany(mappedBy = "division")
    private List<Veterinarian> veterinarians;

    @ManyToOne
    @JoinColumn(name = "veterinarian_clinic_id")
    private VetClinic vetClinic;
}
