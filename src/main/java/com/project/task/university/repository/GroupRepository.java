package com.project.task.university.repository;

import com.project.task.university.model.Group;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @NonNull List<Group> findAll();
}
