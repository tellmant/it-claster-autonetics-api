package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.exception.NullEntityReferenceException;
import com.autonetics.autonetics.api.model.entity.Staff;
import com.autonetics.autonetics.api.repository.ShopRepository;
import com.autonetics.autonetics.api.repository.StaffRepository;
import com.autonetics.autonetics.api.repository.StaffTypeRepository;
import com.autonetics.autonetics.api.service.StaffService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    private final StaffTypeRepository staffTypeRepository;
    private final ShopRepository shopRepository;


    @Override
    public Staff create(Staff staff) {
        if (staff != null) {
            return staffRepository.save(staff);
        }
        throw new NullEntityReferenceException("Staff cannot be 'null'.");
    }

    @Override
    public Staff update(Staff staff) {
        if (staff != null) {
            return staffRepository.save(staff);
        }
        throw new NullEntityReferenceException("Staff cannot be 'null'.");
    }

    @Override
    public void delete(long id) {
        Staff staff = readById(id);
        staffRepository.delete(staff);
    }

    @Override
    public Staff readById(long id) {
        return staffRepository.findById(id).orElseThrow(
                ()->new EntityNotFoundException("Staff with id " + id + " not found."));
    }

    @Override
    public List<Staff> getAll() {
        return staffRepository.findAll();
    }

    @Override
    public List<Staff> getByFirstNameAndLastName(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return staffRepository.findByFirstNameAndLastName(firstName, lastName);
        }
        throw new NullEntityReferenceException("First name and last name cannot be 'null'.");
    }

    @Override
    public Staff getByEmail(String email) {
        if (email != null) {
            return staffRepository.findByEmail(email);
        }
        throw new NullEntityReferenceException("Email cannot be 'null'.");
    }

    @Override
    public Staff getByPhoneNumber(String phoneNumber) {
        if (phoneNumber != null) {
            return staffRepository.findByPhoneNumber(phoneNumber);
        }
        throw new NullEntityReferenceException("Phone number cannot be 'null'.");
    }

    @Override
    public List<Staff> getByShopId(Long shopId) {
        if (shopRepository.findById(shopId).isPresent()) {
            return staffRepository.findByShopId(shopId);
        }
        throw new NullEntityReferenceException("Shop id cannot be 'null'.");
    }

    @Override
    public List<Staff> getByStaffTypeIdAndShopId(Long staffTypeId, Long shopId) {
       if (staffTypeRepository.findById(staffTypeId).isPresent() && shopRepository.findById(shopId).isPresent()) {
            return staffRepository.findByStaffTypeIdAndShop_Id(staffTypeId, shopId);
        }
        throw new NullEntityReferenceException("Staff type id and shop id cannot be 'null'.");
    }
}
