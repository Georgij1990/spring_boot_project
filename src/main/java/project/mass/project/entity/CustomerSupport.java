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

    @Column(name = "record")
    @ElementCollection
    @CollectionTable(name = "training_records", joinColumns = @JoinColumn(name = "customer_support_id"))
    private List<String> trainingRecords = new ArrayList<>();

    @OneToMany(mappedBy = "customerSupport")
    private List<Case> cases = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private CustomerSupport mentor;

    public CustomerSupport() {
    }

    public CustomerSupport(LocalDate hireDate, ContractType contractType, Double salary, Person person, List<String> trainingRecords) {
        super(hireDate, contractType, salary, person);
        setTrainingRecords(trainingRecords);
        setCases(null);
        setMentor(null);
    }

    public List<String> getTrainingRecords() {
        return trainingRecords;
    }

    public void setTrainingRecords(List<String> trainingRecords) {
        if (Utility.hasNotNull(Collections.singletonList(trainingRecords))) {
            trainingRecords.stream().filter(Objects::nonNull).filter(tR -> !tR.isBlank()).forEach(tR -> this.trainingRecords.add(tR));
        }
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        if (Utility.hasNotNull(Collections.singletonList(cases))) {
            cases.stream().filter(Objects::nonNull)
                    .forEach(c -> this.cases.add(c));
        }
    }

    public CustomerSupport getMentor() {
        return mentor;
    }

    public void setMentor(CustomerSupport mentor) {
        this.mentor = mentor;
    }
}
