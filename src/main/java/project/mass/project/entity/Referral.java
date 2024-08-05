package project.mass.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "referral")
public class Referral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "referral_number")
    private Long referralNumber;

    @Column(name = "date_from")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    private LocalDate dateTo;

    @Column(name = "referral_name")
    private String referralName;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
