package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.GenericEntity;
import com.kodilla.ecommercee.GenericEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DbServiceForUserController {
    private final GenericEntityRepository genericEntityRepository;

    public void addNewUser(final GenericEntity genericEntity) {
        genericEntityRepository.save(genericEntity);
    }
}
