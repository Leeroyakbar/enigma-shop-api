package com.enigma.wmb_api.controller;


import com.enigma.wmb_api.constant.ConstantAPI;
import com.enigma.wmb_api.dto.request.SearchCustomerRequest;
import com.enigma.wmb_api.dto.response.CommonResponse;
import com.enigma.wmb_api.dto.response.PagingResponse;
import com.enigma.wmb_api.entity.Customer;
import com.enigma.wmb_api.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.hibernate.id.enhanced.CustomOptimizerDescriptor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ConstantAPI.CUSTOMER_API)
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping
    public ResponseEntity<CommonResponse<Customer>> create(
            @RequestBody Customer customer
    ){
        Customer newCustomer = customerService.create(customer);

        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully added customer")
                .data(newCustomer)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Customer>>> getAll(
            @RequestParam (name = "page", defaultValue = "1") Integer page,
            @RequestParam (name = "size", defaultValue = "10") Integer size,
            @RequestParam (name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam (name = "direction", defaultValue = "asc") String direction,
            @RequestParam (name = "name", required = false) String name
    ){
        SearchCustomerRequest request = SearchCustomerRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .name(name)
                .build();

        Page<Customer> customers = customerService.getAll(request);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(customers.getTotalPages())
                .totalElements(customers.getTotalElements())
                .page(customers.getPageable().getPageNumber())
                .size(customers.getPageable().getPageSize())
                .hasNext(customers.hasNext())
                .hasPrevious(customers.hasPrevious())
                .build();

        CommonResponse<List<Customer>> response = CommonResponse.<List<Customer>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully get all customer")
                .data(customers.getContent())
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CommonResponse<Customer>> getById(
            @PathVariable String id
    ){
        Customer customer = customerService.getById(id);

        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message("success get customer")
                .data(customer)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Customer>> update(
            @RequestBody Customer customer
    ){
        Customer newCustomer = customerService.update(customer);

        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully updated customer")
                .data(newCustomer)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CommonResponse<?>> delete(
            @PathVariable String id
    ){
        customerService.delete(id);
        CommonResponse<Object> response = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully deleted customer")
                .build();

        return ResponseEntity.ok(response);
    }
}
