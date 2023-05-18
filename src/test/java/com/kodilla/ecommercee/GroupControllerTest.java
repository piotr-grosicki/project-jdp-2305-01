package com.kodilla.ecommercee;
import com.kodilla.ecommercee.controller.GroupController;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class GroupControllerTest {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupController groupController;

    @Test
    public void getGroupsTest(){
        //Given
        List<Group> groupList = new ArrayList<>();
        groupList.add(new Group(1L, "group1"));
        groupList.add(new Group(2L, "group2"));
        groupList.add(new Group(3L, "group3"));
        groupRepository.saveAll(groupList);
        //When
        ResponseEntity result = groupController.getGroups();
        //Then
        assertEquals(3, ((List<Group>) result.getBody()).size());
    }

    @Test
    public void creatGroupTest(){
        //Given
        GroupDto group1 = new GroupDto(1L,"ABC");
        //When
        ResponseEntity entity = groupController.createGroup(group1);
        //Then
        assertEquals(1,groupRepository.findAll().size());
        assertEquals(200,entity.getStatusCodeValue());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @Order(1)
    public void getGroupTest() throws GroupNotFoundException {
        //Given
        List<Group> groupList = new ArrayList<>();
        groupList.add(new Group(1L, "group1"));
        groupList.add(new Group(2L, "group2"));
        groupList.add(new Group(3L, "group3"));
        groupRepository.saveAll(groupList);
        //When
        ResponseEntity result = groupController.getGroup(groupList.get(0).getGroupId());
        //Then
        assertNotNull(result);
        GroupDto resultGroup = (GroupDto)result.getBody();
        assertEquals("group1", resultGroup.getGroupName());
    }
    @Test
    public void updateGroupTest() throws GroupNotFoundException {
        //Given
        List<Group> groupList = new ArrayList<>();
        groupList.add(new Group(1L, "group1"));
        groupList.add(new Group(2L, "group2"));
        groupList.add(new Group(3L, "group3"));
        groupRepository.saveAll(groupList);
        //When
        groupController.updateGroup(new GroupDto(1L,"group10"));
        //Then
        assertEquals("group10",groupRepository.findById(1L).get().getGroupName());
    }
    @Test
    public void updateGroupExceptionTest() {
        assertThrows(GroupNotFoundException.class, ()->{
            groupController.updateGroup(new GroupDto(1L,"A"));
        });
        assertEquals(0,groupRepository.findAll().size());
    }
    @Test
    public void getGroupExceptionTest() {
        assertThrows(GroupNotFoundException.class, () -> {
            groupController.getGroup(1L);
        });
    }

}
