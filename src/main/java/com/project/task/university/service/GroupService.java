package com.project.task.university.service;

import com.project.task.university.model.Group;
import com.project.task.university.repository.GroupRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GroupService {

    @NonNull
    private final GroupRepository groupRepository;

    public Optional<Group> getGroupById(Long id) {
        return groupRepository.findById(id);
    }

    public Group addGroup(Group group) {
        return groupRepository.save(group);
    }

    public void deleteGroup(Group group) {
        groupRepository.delete(group);
    }

    public void deleteGroupById(Long id) {
        groupRepository.deleteById(id);
    }

    public long getTotalGroupsCount() {
        return groupRepository.count();
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }
}
