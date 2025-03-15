package com.dct.nextgen.repositories;

import com.dct.nextgen.entity.ProjectImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectImageRepository extends JpaRepository<ProjectImage, Integer> {
}
