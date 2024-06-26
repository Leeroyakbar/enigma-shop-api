package com.enigma.wmb_api.service.impl;


import com.enigma.wmb_api.dto.request.SearchMenuRequest;
import com.enigma.wmb_api.entity.Menu;
import com.enigma.wmb_api.repository.MenuRepository;
import com.enigma.wmb_api.service.MenuService;
import com.enigma.wmb_api.specification.MenuSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;


    @Override
    public Menu create(Menu menu) {
       return menuRepository.save(menu);
    }

    @Override
    public Page<Menu> getAll(SearchMenuRequest request) {
        if(request.getPage() <= 0) request.setPage(1);
        Sort sort = Sort.by(Sort.Direction.fromString(request.getDirection()), request.getSortBy());
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        Specification<Menu> specification = MenuSpecification.getSpecification(request);
        return menuRepository.findAll(specification, pageable);
    }

    @Override
    public Menu findById(String id) {
        Optional<Menu> menu = menuRepository.findById(id);
        if(menu.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu is not found");

        return menu.get();
    }


    @Override
    public void deleteById(String id) {
        Menu menu = findById(id);
        menuRepository.delete(menu);
    }
}
