package project.mass.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer_support")
public class CustomerSupport extends Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ElementCollection
    @CollectionTable(name = "training_records", joinColumns = @JoinColumn(name = "customer_support_id"))
    @Column(name = "record")
    private List<String> trainingRecords;

    @OneToMany(mappedBy = "customer_suport")
    private List<Case> cases;
}
