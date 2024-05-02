package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.exception.NullEntityReferenceException;
import com.autonetics.autonetics.api.model.entity.Class;
import com.autonetics.autonetics.api.repository.ClassRepository;
import com.autonetics.autonetics.api.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;

    @Override
    public Class create(Class classEntity) {
        if (classEntity != null) {
            return classRepository.save(classEntity);
        }
        throw new NullEntityReferenceException("Class cannot be 'null'.");
    }

    @Override
    public Class readById(long id) {
        return classRepository.findById(id)
                .orElseThrow(
                        () -> new NullEntityReferenceException("Class with id " + id + " not found.")
                );
    }

    @Override
    public Class update(Class classEntity) {
        if (classEntity != null) {
            return classRepository.save(classEntity);
        }
        throw new NullEntityReferenceException("Class cannot be 'null'.");
    }

    @Override
    public void delete(long id) {
        Class classEntity = readById(id);
        classRepository.delete(classEntity);
    }

    @Override
    public List<Class> getAll() {
        return classRepository.findAll();
    }
}
