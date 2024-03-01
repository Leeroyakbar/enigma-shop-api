package com.enigma.wmb_api.service.impl;

import com.enigma.wmb_api.entity.M_Table;
import com.enigma.wmb_api.repository.M_TableRepository;
import com.enigma.wmb_api.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {
    private final M_TableRepository tableRepository;

    @Override
    public void create(M_Table table) {
        tableRepository.save(table);
    }

    @Override
    public List<M_Table> getAll() {
        return tableRepository.findAll();
    }

    @Override
    public M_Table findById(String id) {
        Optional<M_Table> table = tableRepository.findById(id);
        if(table.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Table is not found");
        return table.get();
    }


    @Override
    public void delete(String id) {
        M_Table mTable = findById(id);
        tableRepository.delete(mTable);
    }
}
