package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@DisplayName("Group test suite")
class GroupTestSuite {

    @Autowired
    private GroupRepository groupRepository;

    public static List<Group> getGroups() {
        List<Group> groupList = new ArrayList<>();
        groupList.add(new Group(1L, "electronic"));
        groupList.add(new Group(2L, "tools"));
        return groupList;
    }

    @Test
    @DisplayName("Test get groups")
    void testGetGroups() {
        //Given
        List<Group> groupList = getGroups();

        //When
        groupRepository.saveAll(groupList);
        List<Group> list = groupRepository.findAll();

        //Then
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("Test add group")
    void testAddGroup() {
        //Given
        List<Group> groupList = getGroups();

        //When
        groupList.add(new Group(3L, "cars"));
        groupRepository.saveAll(groupList);
        List<Group> list = groupRepository.findAll();

        //Then
        assertEquals(3, list.size());
    }

    @Test
    @DisplayName("Test get group")
    void testGetGroup() {
        //Given
        List<Group> group = getGroups();
        groupRepository.saveAll(group);

        //When
        Optional<Group> findGroup = groupRepository.findById(1L);

        //Then
        assertTrue(findGroup.isPresent());
    }

    @Test
    @DisplayName("Test update group")
    void testUpdateGroup() {
        //Given
        List<Group> group = getGroups();
        groupRepository.saveAll(group);

        Optional<Group> result = groupRepository.findById(1L);
        System.out.println(result.get().getGroupName());

        //Then
        group.set(0, new Group(1L, "cars"));
        groupRepository.saveAll(group);
        System.out.println(groupRepository.findById(1L).get().getGroupName());

        //Then
        assertEquals("cars", group.get(0).getGroupName());
        assertEquals(2, group.size());
    }

    @Test
    @DisplayName("Test group save with products")
    void testGroupSaveWithProducts() {
        //Given
        Product computer = new Product(1L, "Computer", "Super Computer", 1, new BigDecimal(2500));
        Group electronic = new Group(1L, "Electronic");
        computer.setGroup(electronic);
        electronic.getProductList().add(computer);

        //When
        groupRepository.save(electronic);
        Long id = electronic.getGroupId();

        //Then
        assertNotEquals(0L, id);
        assertTrue(groupRepository.findById(id).isPresent());
    }
}