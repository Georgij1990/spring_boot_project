package project.mass.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pet_owner_id")
    private PetOwner petOwner;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Person() {
    }

    public Person(LocalDate birthDate, String email, String firstName, String lastName, String phoneNumber) {
        this.birthDate = birthDate;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PetOwner getPetOwner() {
        return petOwner;
    }

    public void setPetOwner(PetOwner petOwner) {
        this.petOwner = petOwner;
    }

    public Veterinarian getVeterinarian() {
        return (Veterinarian) employee;
    }

    public void setVeterinarian(Veterinarian veterinarian) {
        this.employee = veterinarian;
    }

    public CustomerSupport getCustomerSupport() {
        return (CustomerSupport) employee;
    }

    public void setCustomerSupport(CustomerSupport customerSupport) {
        this.employee = customerSupport;
    }

    @PrePersist
    @PreUpdate
    private void validatePerson() {
        if (petOwner == null && employee == null) {
            throw new IllegalStateException("Either PetOwner or Employee must be populated");
        }
    }
}
