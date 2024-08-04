package project.mass.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "employee")
public abstract class Employee extends Person {

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

    public Employee() {}

    public Employee(LocalDate birthDate, String email, String firstName, String lastName, String phoneNumber, LocalDate hireDate, String contactType, Double salary) {
        super(birthDate, email, firstName, lastName, phoneNumber);
        this.hireDate = hireDate;
        this.contactType = contactType;
        this.salary = salary;
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
}
