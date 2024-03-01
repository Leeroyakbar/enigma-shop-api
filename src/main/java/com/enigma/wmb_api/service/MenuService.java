package com.enigma.wmb_api.service;

import com.enigma.wmb_api.dto.request.SearchMenuRequest;
import com.enigma.wmb_api.entity.Menu;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MenuService {
    Menu create(Menu menu);

    Page<Menu> getAll(SearchMenuRequest request);

    Menu findById(String id);

    void deleteById(String id);
}
