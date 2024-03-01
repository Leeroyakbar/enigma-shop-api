package com.enigma.wmb_api.service.impl;

import com.enigma.wmb_api.entity.TransType;
import com.enigma.wmb_api.repository.TransTypeRepository;
import com.enigma.wmb_api.service.TransTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class TransTypeImpl implements TransTypeService {

    private final TransTypeRepository transTypeRepository;

    @Override
    public TransType create(TransType transType) {
        return transTypeRepository.save(transType);
    }

    @Override
    public List<TransType> getAll() {
        return transTypeRepository.findAll();
    }
}
