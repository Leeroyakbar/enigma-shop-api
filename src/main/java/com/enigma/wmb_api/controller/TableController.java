package com.enigma.wmb_api.controller;


import com.enigma.wmb_api.constant.ConstantAPI;
import com.enigma.wmb_api.dto.response.CommonResponse;
import com.enigma.wmb_api.entity.M_Table;
import com.enigma.wmb_api.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ConstantAPI.TABLE_API)
public class TableController {
    private final TableService tableService;

    @PostMapping
    public ResponseEntity<CommonResponse<M_Table>> create(
            @RequestBody M_Table table
    ){
        tableService.create(table);
        CommonResponse<M_Table> response = CommonResponse.<M_Table>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully added Table")
                .data(table)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<M_Table>>> getAll(){
        List<M_Table> mTables = tableService.getAll();
        CommonResponse<List<M_Table>> response = CommonResponse.<List<M_Table>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully get all table")
                .data(mTables)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CommonResponse<Object>>delete(
            @PathVariable String id
    ){
        tableService.delete(id);
        CommonResponse<Object> response = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully deleted table")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
