package com.danielwestermann.PM_App.repository;

import com.danielwestermann.PM_App.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>  {

}
