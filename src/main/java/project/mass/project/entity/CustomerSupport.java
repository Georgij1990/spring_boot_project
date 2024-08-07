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

    @OneToMany(mappedBy = "customerSupport")
    private List<Case> cases;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private CustomerSupport mentor;

    @OneToMany(mappedBy = "mentor")
    private List<CustomerSupport> mentees = new ArrayList<>();
}
