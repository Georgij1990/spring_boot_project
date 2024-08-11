package project.mass.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer_support")
public class CustomerSupport extends Employee {

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

    public CustomerSupport(LocalDate hireDate, String contractType, Double salary, Person person, List<String> trainingRecords) {
        super(hireDate, contractType, salary, person);
        this.trainingRecords = trainingRecords;
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
