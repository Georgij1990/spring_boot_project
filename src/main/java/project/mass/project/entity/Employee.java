package project.mass.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

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

    @Column(name = "contact_type")
    private String contactType;

    @Column(name = "salary")
    private Double salary;

    @OneToOne(mappedBy = "employee")
    private Person person;

    public Employee() {
    }

    public Employee(LocalDate hireDate, String contactType, Double salary, Person person) {
        this.hireDate = hireDate;
        this.contactType = contactType;
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

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
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
