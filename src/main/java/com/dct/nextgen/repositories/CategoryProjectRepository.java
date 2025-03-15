package com.dct.nextgen.repositories;

import com.dct.nextgen.entity.CategoryProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryProjectRepository extends JpaRepository<CategoryProject, Integer> {
}
