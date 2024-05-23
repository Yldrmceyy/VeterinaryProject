package dev.cey.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id", columnDefinition = "serial")
    private int id;

    @Column(name = "doctor_name")
    private String name;
    @Column(name = "doctor_phone")
    private String phone;
    @Column(name = "doctor_mail")
    private String mail;
    @Column(name = "doctor_address")
    private String address;
    @Column(name = "doctor_city")
    private String city;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<AvailableDate> availableDate;
}