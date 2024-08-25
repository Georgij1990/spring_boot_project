package project.mass.project.entity;

import jakarta.persistence.*;
import project.mass.project.Utility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    public CustomerSupport() {
    }

    public CustomerSupport(LocalDate hireDate, ContractType contractType, Double salary, Person person, List<String> trainingRecords) {
        super(hireDate, contractType, salary, person);
        setTrainingRecords(trainingRecords);
        setMentor(null);
    }

    public List<String> getTrainingRecords() {
        return trainingRecords;
    }

    public void setTrainingRecords(List<String> trainingRecords) {
        if (trainingRecords == null) {
            this.trainingRecords = new ArrayList<>();
        } else {
            if (Utility.hasNotNull(Collections.singletonList(trainingRecords))) {
                this.trainingRecords = new ArrayList<>();
                trainingRecords.stream().filter(Objects::nonNull)
                        .forEach(tR -> this.trainingRecords.add(tR));
            } else {
                this.trainingRecords = new ArrayList<>();
            }
        }
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        if (cases == null) {
            this.cases = new ArrayList<>();
        } else {
            if (Utility.hasNotNull(Collections.singletonList(cases))) {
                cases.stream().filter(Objects::nonNull)
                        .forEach(c -> this.cases.add(c));
            } else {
                this.cases = new ArrayList<>();
            }
        }
    }

    public CustomerSupport getMentor() {
        return mentor;
    }

    public void setMentor(CustomerSupport mentor) {
        this.mentor = mentor;
    }
}
