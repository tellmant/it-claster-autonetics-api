package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findByFirstNameAndLastName(String firstName, String lastName);
    Staff findByEmail(String email);
    Staff findByPhoneNumber(String phoneNumber);
    List<Staff> findByShopId(Long shopId);
    List<Staff> findByStaffTypeIdAndShop_Id(Long staffTypeId, Long shopId);
}