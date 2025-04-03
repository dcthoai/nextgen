package com.dct.nextgen.repositories;

import com.dct.nextgen.dto.mapping.ICategoryDTO;
import com.dct.nextgen.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT c.id, c.name FROM category c", nativeQuery = true)
    Page<ICategoryDTO> findAllWithPaging(Pageable pageable);

    @Query(value = "SELECT c.id, c.name FROM category c LIMIT 20", nativeQuery = true)
    List<ICategoryDTO> findAllNonPaging();

    @Query(
        value = """
            SELECT COUNT(DISTINCT cp.project_id)
            FROM category c
            JOIN category_project cp on c.id = cp.category_id
            WHERE c.id = ?1
        """,
        nativeQuery = true
    )
    long countCategoryProjectQuantity(Integer categoryId);

    @Query(value = "SELECT c.id FROM category c WHERE c.id IN (?1)", nativeQuery = true)
    List<Integer> findAllCategoryIdsIn(Iterable<Integer> categoryIds);

    @Query(
        value = """
            SELECT c.id, c.name
            FROM category c
            JOIN category_project cp ON c.id = cp.category_id
            WHERE cp.project_id = ?1
        """,
        nativeQuery = true
    )
    List<ICategoryDTO> findAllByProjectId(Integer projectId);
}
