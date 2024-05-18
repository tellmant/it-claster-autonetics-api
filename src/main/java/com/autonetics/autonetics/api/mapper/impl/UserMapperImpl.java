package com.autonetics.autonetics.api.mapper.impl;

import com.autonetics.autonetics.api.mapper.UserMapper;
import com.autonetics.autonetics.api.model.entity.Client;
import com.autonetics.autonetics.api.model.entity.Staff;

public class UserMapperImpl implements UserMapper {

    @Override
    public Client toClient(Staff staff) {
        if (staff == null) {
            return null;
        }

        Client client = new Client();

        if (staff.getId() != null) {
            client.setId(staff.getId().intValue());
        }
        client.setFirstName(staff.getFirstName());
        client.setLastName(staff.getLastName());
        client.setBirthDate(staff.getBirthDate());
        client.setGender(staff.getGender());
        client.setPhoneNumber(staff.getPhoneNumber());
        client.setEmail(staff.getEmail());
        client.setPassword(staff.getPassword());
        client.setRole(staff.getRole());
        client.setUpdatedBy(staff.getUpdatedBy());
        client.setUpdatedOn(staff.getUpdatedOn());

        return client;
    }
}
