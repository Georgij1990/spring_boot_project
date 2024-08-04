package project.mass.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
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

    public CustomerSupport() {}

    public CustomerSupport(LocalDate birthDate, String email, String firstName, String lastName, String phoneNumber, LocalDate hireDate, String contactType, Double salary, List<String> trainingRecords) {
        super(birthDate, email, firstName, lastName, phoneNumber, hireDate, contactType, salary);
        this.trainingRecords = trainingRecords;
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
}
