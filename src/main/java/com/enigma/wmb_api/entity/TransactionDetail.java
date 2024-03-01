package com.enigma.wmb_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_bill_detail")
public class TransactionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "qty", nullable = false)
    private Integer qty;
}
