package com.singidunum.delivery.controller;

import com.singidunum.delivery.dto.BaseDto;
import com.singidunum.delivery.service.BaseCrudService;
import jakarta.websocket.server.PathParam;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class BaseCrudController<T extends BaseDto> {
    private final BaseCrudService baseCrudService;


    public BaseCrudController(BaseCrudService baseCrudService) {
        this.baseCrudService = baseCrudService;
    }

    @GetMapping("/list")
    public List<T> getList() {
        return baseCrudService.getList();
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody T dto) {
        baseCrudService.create(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathParam("id") Long id) {
        baseCrudService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
