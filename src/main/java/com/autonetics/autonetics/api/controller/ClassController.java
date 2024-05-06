package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.ClassMapper;
import com.autonetics.autonetics.api.model.entity.Class;
import com.autonetics.autonetics.api.model.request.NewClassRequest;
import com.autonetics.autonetics.api.model.response.ClassCreated;
import com.autonetics.autonetics.api.model.response.ClassDeleted;
import com.autonetics.autonetics.api.model.response.ClassDto;
import com.autonetics.autonetics.api.model.response.ClassUpdated;
import com.autonetics.autonetics.api.service.ClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/class")
public class ClassController {

    private final ClassService classService;
    private final ClassMapper classMapper;

    public ClassController(ClassService classService, ClassMapper classMapper) {
        this.classService = classService;
        this.classMapper = classMapper;
    }

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<ClassDto>> getAll() {
        List<Class> classList = classService.getAll();
        List<ClassDto> classDtoList = classList.stream()
                .map(classMapper::toDto)
                .toList();

        return new ResponseEntity<>(classDtoList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<ClassDto> readById(@PathVariable long id) {
        Class classEntity = classService.readById(id);
        return new ResponseEntity<>(classMapper.toDto(classEntity), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClassCreated> create(@Validated @RequestBody NewClassRequest newClassRequest) {
        Class classEntity = classMapper.toEntity(newClassRequest);
        classEntity.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        classEntity.setUpdatedOn(Instant.now());

        Class createdClassEntity = classService.create(classEntity);

        return new ResponseEntity<>(new ClassCreated(createdClassEntity.getId()), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClassUpdated> update(@PathVariable long id, @Validated @RequestBody NewClassRequest newClassRequest) {
        Class classEntity = classMapper.toEntity(newClassRequest);
        classEntity.setId((int) id);
        classEntity.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        classEntity.setUpdatedOn(Instant.now());

        Class updatedClassEntity = classService.update(classEntity);

        return new ResponseEntity<>(new ClassUpdated(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClassDeleted> delete(@PathVariable long id) {
        classService.delete(id);
        return new ResponseEntity<>(new ClassDeleted(id), HttpStatus.OK);
    }
}
