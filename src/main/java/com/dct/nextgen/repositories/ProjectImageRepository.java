package com.dct.nextgen.repositories;

import com.dct.nextgen.dto.mapping.IProjectImageDTO;
import com.dct.nextgen.entity.ProjectImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectImageRepository extends JpaRepository<ProjectImage, Integer> {

    @Query(value = "SELECT pi.url FROM project_image pi WHERE pi.project_id = ?1 ORDER BY pi.position", nativeQuery = true)
    List<String> findAllImageUrlByProjectId(Integer projectId);

    @Query(
        value = """
            SELECT pi.id, pi.url, pi.position
            FROM project_image pi
            WHERE pi.project_id = ?1
            ORDER BY pi.position
        """,
        nativeQuery = true
    )
    List<IProjectImageDTO> findAllByProjectId(Integer projectId);
}
