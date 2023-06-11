package com.singidunum.delivery.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.singidunum.delivery.dto.BaseDto;
import com.singidunum.delivery.dto.FilterDto;
import com.singidunum.delivery.dto.FilterIdList;
import com.singidunum.delivery.dto.IdDto;
import com.singidunum.delivery.dto.OrderParam;
import com.singidunum.delivery.service.BaseCrudService;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


public abstract class BaseCrudController<T extends BaseDto> {
    private final BaseCrudService baseCrudService;
    private final ObjectMapper objectMapper;


    public BaseCrudController(BaseCrudService baseCrudService) {
        this.baseCrudService = baseCrudService;
        this.objectMapper = new ObjectMapper();
    }

    private static <F> F getField(Object object, String fieldName, Class<F> fieldType) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return fieldType.cast(field.get(object));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Object getField(Object object, String fieldName) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity<List<T>> getList(
        @RequestParam(value = "filter", required = false) String filter,
        @RequestParam(value = "range", required = false) List<String> range,
        @RequestParam(value = "order", required = false) String order) throws JsonProcessingException {
        FilterDto filterDto = objectMapper.readValue(filter, FilterDto.class);
        Integer[] rangeInt =
            {Integer.valueOf(range.get(0).replace("[", "")), Integer.valueOf(range.get(1).replace("]", ""))};
        OrderParam orderParam = objectMapper.readValue(order, OrderParam.class);
        List<T> list = baseCrudService.getList();
        List<T> listResponse = null;
        if (filterDto.getOrder() != null && filterDto.getQ() != null) {
            listResponse = list.stream()
                .filter(elem -> getField(elem, filterDto.getOrder(), Object.class).toString()
                    .contains(filterDto.getQ()))
                .toList();
        } else if (orderParam.getOrder() != null && orderParam.getField() != null && list.size() != 0) {
            try {
                Comparator<T> comparator =
                    Comparator.comparing(elem -> getField(elem, orderParam.getField(), Long.class));
                if (orderParam.getOrder().equals("DESC")) {
                    comparator = comparator.reversed();
                }
                listResponse = list.stream()
                    .sorted(comparator)
                    .toList()
                    .subList(rangeInt[0], rangeInt[1] + 1 > list.size() ? list.size() : rangeInt[1] + 1);
            } catch (ClassCastException e) {
                Comparator<T> comparator =
                    Comparator.comparing(elem -> Objects.requireNonNull(getField(elem, orderParam.getField()))
                        .toString());
                if (orderParam.getOrder().equals("DESC")) {
                    comparator = comparator.reversed();
                }
                listResponse = list.stream()
                    .sorted(comparator)
                    .toList()
                    .subList(rangeInt[0], rangeInt[1] + 1 > list.size() ? list.size() : rangeInt[1] + 1);
            }

        } else {
            listResponse = list.stream()
                .sorted((o1, o2) -> (int) (o1.getId() - o2.getId()))
                .toList()
                .subList(rangeInt[0], rangeInt[1] + 1 > list.size() ? list.size() : rangeInt[1] + 1);
        }
        return ResponseEntity.ok()
            .header("Access-Control-Expose-Headers", HttpHeaders.CONTENT_RANGE)
            .header(
                HttpHeaders.CONTENT_RANGE,
                String.format(
                    "%s-%s/%d",
                    rangeInt[0],
                    rangeInt[1] > list.size() ? list.size() : rangeInt[1],
                    list.size()))
            .body(listResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok()
            .body((T) baseCrudService.findById(Long.valueOf(id)));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody T dto) {
        return new ResponseEntity<>(baseCrudService.create(dto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(
        @PathVariable("id") Long id,
        @RequestBody T dto) {
        baseCrudService.updateById(id, dto);
        return new ResponseEntity<>(new IdDto(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        baseCrudService.deleteById(id);
        return new ResponseEntity<>(new IdDto(id), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity deleteById(@RequestParam(value = "filter", required = false) String filter)
        throws JsonProcessingException {
        FilterIdList filterIdList = objectMapper.readValue(filter, FilterIdList.class);
        filterIdList.getId().forEach(id -> baseCrudService.deleteById(id));
        return new ResponseEntity<>(filterIdList.getId(), HttpStatus.OK);
    }
}
