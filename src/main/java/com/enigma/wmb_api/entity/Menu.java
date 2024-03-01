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
@Table(name = "m_menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "menu_name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false, columnDefinition = "BIGINT CHECK (price >= 0)")
    private Long price;
}
