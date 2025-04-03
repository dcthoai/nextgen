package com.dct.nextgen.repositories;

import com.dct.nextgen.dto.mapping.IProjectDTO;
import com.dct.nextgen.dto.mapping.IProjectDetailDTO;
import com.dct.nextgen.entity.Project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query(
        value = """
            SELECT p.id, p.name, p.customer,
                p.category_name AS customerName,
                p.thumbnail_rect as thumbnailRect,
                p.thumbnail_square as thumbnailSquare
            FROM project p
        """,
        nativeQuery = true
    )
    Page<IProjectDTO> findAllWithPaging(Pageable pageable);

    @Query(
        value = """
            SELECT p.id, p.name, p.customer,
                p.category_name AS customerName,
                p.thumbnail_rect as thumbnailRect,
                p.thumbnail_square as thumbnailSquare
            FROM project p
            LIMIT 20
        """,
        nativeQuery = true
    )
    List<IProjectDTO> findAllNonPaging();

    @Query(
        value = """
            SELECT p.id, p.name, p.customer,
                p.category_name AS customerName,
                p.thumbnail_rect as thumbnailRect,
                p.thumbnail_square as thumbnailSquare
            FROM project p
            JOIN category_project cp on p.id = cp.project_id
            WHERE cp.category_id = ?1
        """,
        nativeQuery = true
    )
    Page<IProjectDTO> findAllByCategoryWithPaging(Integer categoryId, Pageable pageable);

    @Query(
        value = """
            SELECT p.id, p.name, p.customer,
                p.category_name AS customerName,
                p.thumbnail_rect as thumbnailRect,
                p.thumbnail_square as thumbnailSquare
            FROM project p
            JOIN category_project cp on p.id = cp.project_id
            WHERE cp.category_id = ?1
            LIMIT 20
        """,
        nativeQuery = true
    )
    List<IProjectDTO> findAllByCategoryNonPaging(Integer categoryId);

    @Query(
        value = """
            SELECT p.id,
                p.name,
                p.sub_name as subName,
                p.thumbnail_rect as thumbnailRect,
                p.thumbnail_square as thumbnailSquare,
                p.title,
                p.description,
                p.more_description as moreDescription,
                p.category_name AS customerName,
                p.customer,
                p.finished_date as finishedDate,
                p.link,
                p.link_title as linkTitle
            FROM project p
            WHERE p.id = ?1
        """,
        nativeQuery = true
    )
    Optional<IProjectDetailDTO> findProjectById(Integer projectId);
}
