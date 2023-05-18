package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final GroupMapper groupMapper;

    @GetMapping
    public ResponseEntity <List<GroupDto>> getGroups() {
        List<Group> groupList = groupService.getGroups();
        return ResponseEntity.ok(groupMapper.mapToGroupDtoList(groupList));
    }

    @PostMapping
    public ResponseEntity<Void> createGroup(@RequestBody GroupDto groupDto){
        Group group = groupMapper.mapToGroup(groupDto);
        groupService.saveGroup(group);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{groupId}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable Long groupId) throws GroupNotFoundException {
        return ResponseEntity.ok(groupMapper.mapToGroupDto(groupService.getGroup(groupId)));
    }

    @PutMapping
    public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto groupDto) throws GroupNotFoundException{
        Group group = groupMapper.mapToGroup(groupDto);
        groupService.getGroup(group.getGroupId());
        Group saveGroup = groupService.saveGroup(group);
        return ResponseEntity.ok(groupMapper.mapToGroupDto(saveGroup));
    }
}
