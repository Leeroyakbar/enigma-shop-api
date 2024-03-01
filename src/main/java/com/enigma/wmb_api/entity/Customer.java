package com.enigma.wmb_api.entity;


import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "m_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "mobile_phone_no")
    private String mobilePhone;

    @Column(name = "is_member")
    private Boolean isMember;
}
