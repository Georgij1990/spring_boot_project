package project.mass.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import project.mass.project.Utility;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotNull
    @NotEmpty
    @Column(name = "email")
    private String email;

    @NotNull
    @NotEmpty
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @NotEmpty
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
        setBirthDate(birthDate);
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
    }

    public int getId() {
        return id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("birthDate cannot be null");
        }
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!Utility.validateString(email)) {
            throw new IllegalArgumentException("email cannot be empty");
        }
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (!Utility.validateString(firstName)) {
            throw new IllegalArgumentException("firstName cannot be empty");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (!Utility.validateString(lastName)) {
            throw new IllegalArgumentException("lastName cannot be empty");
        }
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!Utility.validateString(phoneNumber)) {
            throw new IllegalArgumentException("phoneNumber cannot be empty");
        }
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
