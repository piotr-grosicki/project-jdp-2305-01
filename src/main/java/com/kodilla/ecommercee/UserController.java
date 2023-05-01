package com.kodilla.ecommercee;

import com.kodilla.ecommercee.mapper.GenericEntityDto;
import com.kodilla.ecommercee.mapper.GenericEntityMapper;
import com.kodilla.ecommercee.service.DbServiceForUserController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/ecommercee/users")
@RequiredArgsConstructor
public class UserController {

    private final DbServiceForUserController dbService;
    private final GenericEntityMapper mapper;

    @PutMapping
    public ResponseEntity<String> addNewUser(@RequestBody GenericEntityDto genericEntityDto) {
        GenericEntity genericEntity = mapper.mapToGenericEntity(genericEntityDto);
        dbService.addNewUser(genericEntity);
        log.info("New user created in database - id: " + genericEntity.getId());

        return ResponseEntity.ok("New user created.");
    }
}