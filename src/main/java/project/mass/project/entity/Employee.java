package project.mass.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "employee")
public abstract class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "hire_date")
    private LocalDate hireDate;

    @NotNull
    @Enumerated(STRING)
    @Column(name = "contract_type")
    private ContractType contractType;

    @NotNull
    @Column(name = "salary")
    private Double salary;

    @NotNull
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Person person;

    public Employee() {
    }

    public Employee(LocalDate hireDate, ContractType contractType, Double salary, Person person) {
        setHireDate(hireDate);
        setContractType(contractType);
        setSalary(salary);
        setPerson(person);
    }

    public int getId() {
        return id;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        if (hireDate == null) {
            throw new IllegalArgumentException("Hire date cannot be null");
        }
        this.hireDate = hireDate;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contactType) {
        if (contactType == null) {
            throw new IllegalArgumentException("Contact type cannot be null");
        }
        this.contractType = contactType;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        if (salary == null) {
            throw new IllegalArgumentException("Salary cannot be null");
        } else if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.salary = salary;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }
        this.person = person;
    }
}
