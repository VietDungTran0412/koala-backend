package com.example.koalasystem.entity;

import com.example.koalasystem.enums.ReservationStatus;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;
    @Column(name = "number_of_people")
    private Integer numOfPeople;
    @Column(name = "reservation_time")
    private Date reservationTime;
    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_status")
    private ReservationStatus status;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "name", column = @Column(name = "cust_name")),
            @AttributeOverride( name = "phone", column = @Column(name = "cust_phone_number")),
            @AttributeOverride( name = "email", column = @Column(name = "cust_email"))
    })
    private Customer customer;
}
