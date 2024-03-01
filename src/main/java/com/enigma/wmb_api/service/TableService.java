package com.enigma.wmb_api.service;

import com.enigma.wmb_api.entity.M_Table;

import java.util.List;

public interface TableService {
    void create(M_Table table);

    List<M_Table> getAll();

    M_Table findById(String id);

    void delete(String id);
}
