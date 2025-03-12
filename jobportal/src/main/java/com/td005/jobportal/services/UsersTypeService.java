package com.td005.jobportal.services;

import com.td005.jobportal.entity.UsersType;
import com.td005.jobportal.repository.UsersTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersTypeService {

    private final UsersTypeRepository usersTypeRepository;

    @Autowired
    public UsersTypeService(UsersTypeRepository usersTypeRepository)
    {
        this.usersTypeRepository = usersTypeRepository;
    }

    public List<UsersType> getAll()
    {
        return usersTypeRepository.findAll();

    }
}
