package com.enigma.wmb_api.repository;

import com.enigma.wmb_api.entity.M_Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface M_TableRepository extends JpaRepository<M_Table, String> {
}
