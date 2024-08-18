package project.mass.project.entity;

import jakarta.persistence.*;

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

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Enumerated(STRING)
    @Column(name = "contract_type")
    private ContractType contractType;

    @Column(name = "salary")
    private Double salary;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Person person;

    public Employee() {
    }

    public Employee(LocalDate hireDate, ContractType contractType, Double salary, Person person) {
        this.hireDate = hireDate;
        this.contractType = contractType;
        this.salary = salary;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contactType) {
        this.contractType = contactType;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
