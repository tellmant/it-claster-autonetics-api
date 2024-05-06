package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.Staff;

import java.util.List;

public interface StaffService {
    Staff create(Staff staff);
    Staff update(Staff staff);
    void delete(long id);
    Staff readById(long id);
    List<Staff> getAll();
    List<Staff> getByFirstNameAndLastName(String firstName, String lastName);
    Staff getByEmail(String email);
    Staff getByPhoneNumber(String phoneNumber);
    List<Staff> getByShopId(Long shopId);
    List<Staff> getByStaffTypeIdAndShopId(Long staffTypeId, Long shopId);
}
