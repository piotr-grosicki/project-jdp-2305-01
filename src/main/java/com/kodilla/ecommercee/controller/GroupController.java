package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.GenericEntity;
import com.kodilla.ecommercee.domain.GroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;




@RestController
@CrossOrigin("*")
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
public class GroupController extends GenericEntity {

    GroupDto groupDto;

    @GetMapping
    public List<GroupDto> getGroups() {
        return new ArrayList<>();
    }

    @PostMapping
    public void addGroup() {
    }

    @GetMapping(value = "{groupId}")
    public GroupDto getGroup(@PathVariable Long groupId) {
        return new GroupDto(1L, "Tools");
    }

    @PutMapping
    public GroupDto updateGroup(GroupDto groupDto) {
        return new GroupDto(2L, "electronics");
    }
}
