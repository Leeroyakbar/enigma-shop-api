package com.enigma.wmb_api.controller;


import com.enigma.wmb_api.constant.ConstantAPI;
import com.enigma.wmb_api.dto.response.CommonResponse;
import com.enigma.wmb_api.entity.TransType;
import com.enigma.wmb_api.service.TransTypeService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ConstantAPI.TRANS_TYPE_API)
public class TransTypeController {
    private final TransTypeService transTypeService;

    @PostMapping
    public ResponseEntity<CommonResponse<TransType>> create(
            @RequestBody TransType transType
    ){
        TransType newTransType = transTypeService.create(transType);

        CommonResponse<TransType> response = CommonResponse.<TransType>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully added trans type")
                .data(newTransType)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<TransType>>> getAll(){
        List<TransType> transTypes = transTypeService.getAll();
        CommonResponse<List<TransType>> response = CommonResponse.<List<TransType>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully get all trans type")
                .data(transTypes)
                .build();
        return ResponseEntity.ok(response);
    }
}
