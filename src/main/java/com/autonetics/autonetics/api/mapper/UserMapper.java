package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.Client;
import com.autonetics.autonetics.api.model.entity.Staff;

public interface UserMapper {

    Client toClient(Staff staff);
}