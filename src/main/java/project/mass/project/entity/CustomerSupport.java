package project.mass.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import project.mass.project.Utility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customer_support")
public class CustomerSupport extends Employee {

    @NotNull
    @Column(name = "record")
    @ElementCollection
    @CollectionTable(name = "training_records", joinColumns = @JoinColumn(name = "customer_support_id"))
    private List<String> trainingRecords = new ArrayList<>();

    @NotNull
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
        return List.copyOf(trainingRecords);
    }

    public void setTrainingRecords(List<String> trainingRecords) {
        if (Utility.hasNotNull(Collections.singletonList(trainingRecords))) {
            trainingRecords.stream().filter(Objects::nonNull).filter(tR -> !tR.isBlank()).forEach(tR ->  {
                if (!this.trainingRecords.contains(tR)) {
                    this.trainingRecords.add(tR);
                }
            });
        }
    }

    public void addTrainingRecord(String record) {
        if (!Utility.validateString(record)) {
            throw new IllegalArgumentException("Invalid record");
        } else if (this.trainingRecords.contains(record)) {
            throw new IllegalArgumentException("Training record already exists");
        }
        trainingRecords.add(record);
    }

    public void removeTrainingRecord(String record) {
        if (!Utility.validateString(record)) {
            throw new IllegalArgumentException("Invalid record");
        }
        trainingRecords.remove(record);
    }

    public List<Case> getCases() {
        return List.copyOf(cases);
    }

    public void setCases(List<Case> cases) {
        if (Utility.hasNotNull(Collections.singletonList(cases))) {
            cases.stream().filter(Objects::nonNull).forEach(c -> this.cases.add(c));
        }
    }

    public void addCase(Case cases) {
        if (cases == null) {
            throw new IllegalArgumentException("Case that you want to add cannot be null");
        }
        this.cases.add(cases);
    }

    public void removeCase(Case cases) {
        if (cases == null) {
            throw new IllegalArgumentException("Case that you want to delete cannot be null");
        }
        this.cases.remove(cases);
    }

    public CustomerSupport getMentor() {
        return mentor;
    }

    public void setMentor(CustomerSupport mentor) {
        this.mentor = mentor;
    }
}
