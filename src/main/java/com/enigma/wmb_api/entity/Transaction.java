package com.enigma.wmb_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_bill")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "trans_date")
    private Date transDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private M_Table table;

    @ManyToOne
    @JoinColumn(name = "trans_type_id")
    private TransType transType;

}
