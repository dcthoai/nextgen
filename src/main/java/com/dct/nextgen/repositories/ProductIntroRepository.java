package com.dct.nextgen.repositories;

import com.dct.nextgen.dto.mapping.IProductIntroDTO;
import com.dct.nextgen.entity.ProductIntro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductIntroRepository extends JpaRepository<ProductIntro, Integer> {

    @Query(
        value = "SELECT pi.content FROM product_intro pi WHERE pi.product_id = ?1 ORDER BY pi.position",
        nativeQuery = true
    )
    List<String> findAllIntros(Integer productId);

    @Query(
        value = """
            SELECT pi.id, pi.content, pi.position
            FROM product_intro pi
            WHERE pi.product_id = ?1
            ORDER BY pi.position
        """,
        nativeQuery = true
    )
    List<IProductIntroDTO> findAllIntrosByProductId(Integer productId);
}
