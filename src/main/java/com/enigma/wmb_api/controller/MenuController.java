package com.enigma.wmb_api.controller;


import com.enigma.wmb_api.constant.ConstantAPI;
import com.enigma.wmb_api.dto.request.SearchMenuRequest;
import com.enigma.wmb_api.dto.response.CommonResponse;
import com.enigma.wmb_api.dto.response.PagingResponse;
import com.enigma.wmb_api.entity.Menu;
import com.enigma.wmb_api.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ConstantAPI.MENU_API)
public class MenuController {
    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<CommonResponse<Menu>> create(
            @RequestBody Menu menu
    ){
        Menu menuResult = menuService.create(menu);
        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Succesfully added menu")
                .data(menuResult)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Menu>>> getAll(
            @RequestParam(name = "page", defaultValue = "1")Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "name", required = false) String name
    ){
        SearchMenuRequest request = SearchMenuRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .name(name)
                .build();
        Page<Menu> menus = menuService.getAll(request);

        PagingResponse paging = PagingResponse.builder()
                .totalPages(menus.getTotalPages())
                .totalElements(menus.getTotalElements())
                .page(menus.getPageable().getPageNumber() + 1)
                .size(menus.getPageable().getPageSize())
                .hasNext(menus.hasNext())
                .hasPrevious(menus.hasPrevious())
                .build();

        CommonResponse<List<Menu>> response = CommonResponse.<List<Menu>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Seccessfully get all menu")
                .data(menus.getContent())
                .paging(paging)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CommonResponse<Object>> delete(
            @PathVariable String id
    ){
        menuService.deleteById(id);
        CommonResponse<Object> response = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully deleted menu")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
