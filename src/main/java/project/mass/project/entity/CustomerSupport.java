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

    public CustomerSupport() {
    }

    public CustomerSupport(List<String> trainingRecords, List<Case> cases, CustomerSupport mentor, List<CustomerSupport> mentees) {
        this.trainingRecords = trainingRecords;
        this.cases = cases;
        this.mentor = mentor;
        this.mentees = mentees;
    }

    public int getId() {
        return id;
    }

    public List<String> getTrainingRecords() {
        return trainingRecords;
    }

    public void setTrainingRecords(List<String> trainingRecords) {
        this.trainingRecords = trainingRecords;
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }

    public CustomerSupport getMentor() {
        return mentor;
    }

    public void setMentor(CustomerSupport mentor) {
        this.mentor = mentor;
    }

    public List<CustomerSupport> getMentees() {
        return mentees;
    }

    public void setMentees(List<CustomerSupport> mentees) {
        this.mentees = mentees;
    }
}
