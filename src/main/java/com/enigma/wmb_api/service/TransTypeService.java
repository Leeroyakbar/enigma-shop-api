package com.enigma.wmb_api.service;

import com.enigma.wmb_api.entity.TransType;

import java.util.List;

public interface TransTypeService {
    TransType create(TransType transType);

    List<TransType> getAll();
}
