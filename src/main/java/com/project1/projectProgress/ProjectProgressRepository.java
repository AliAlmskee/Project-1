package com.project1.projectProgress;

import com.project1.project.data.Project;
import com.project1.project.data.ProjectStatus;
import com.project1.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectProgressRepository extends JpaRepository<ProjectProgress, Long> {

    Optional<ProjectProgress> findByOffer_ProjectId(Long projectId);
}