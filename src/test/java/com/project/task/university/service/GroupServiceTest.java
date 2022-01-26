package com.project.task.university.service;

import com.project.task.university.UniversityApplication;
import com.project.task.university.model.Group;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Rollback
@Transactional
@SpringBootTest(classes = UniversityApplication.class)
public class GroupServiceTest {

    @Autowired
    private GroupService groupService;

    @Test
    void getGroupByIdTest() {
        Optional<Group> group = groupService.getGroupById(1L);
        assertTrue(group.isPresent());
    }

    @Test
    void addGroupTest() {
        Group group = Group.builder().build();
        Group addedGroup = groupService.addGroup(group);

        assertNotNull(addedGroup);

        Optional<Group> existingGroup = groupService.getGroupById(addedGroup.getGroupId());
        assertTrue(existingGroup.isPresent());
    }

    @Test
    void deleteGroupByIdTest() {
        final Optional<Group> existingGroup = groupService.getGroupById(1L);
        assertTrue(existingGroup.isPresent());

        groupService.deleteGroupById(1L);
        final Optional<Group> checkGroup = groupService.getGroupById(1L);
        assertFalse(checkGroup.isPresent());
    }

    @Test
    void deleteGroupTest() {
        final Optional<Group> existingGroup = groupService.getGroupById(1L);
        assertTrue(existingGroup.isPresent());

        groupService.deleteGroup(existingGroup.get());
        final Optional<Group> checkGroup = groupService.getGroupById(existingGroup.get().getGroupId());
        assertFalse(checkGroup.isPresent());
    }

    @Test
    void getTotalGroupsCountTest() {
        final long count = groupService.getTotalGroupsCount();
        assertEquals(12, count);
    }

    @Test
    void getAllGroupsTest() {
        final List<Group> groups = groupService.getAllGroups();
        assertEquals(12, groups.size());
    }

}
