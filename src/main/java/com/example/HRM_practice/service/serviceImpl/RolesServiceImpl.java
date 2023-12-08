package com.example.HRM_practice.service.serviceImpl;

import com.example.HRM_practice.model.repository.RolesRepository;
import com.example.HRM_practice.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesRepository rolesRepository;
}
