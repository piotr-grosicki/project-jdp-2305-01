package com.kodilla.ecommercee.controller;

public class GroupController {
import com.kodilla.ecommercee.domain.GroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
public class GroupController {

    @GetMapping
    public List<GroupDto> getGroups() {
        return new ArrayList<>();
    }

    @PostMapping
    public void addGroup(GroupDto groupDto) {
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
