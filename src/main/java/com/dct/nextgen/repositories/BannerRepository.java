package com.dct.nextgen.repositories;

import com.dct.nextgen.dto.mapping.IBannerDTO;
import com.dct.nextgen.entity.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {

    @Query(
        value = """
            SELECT b.id,
            b.text_stroke_1 as textStroke1, b.text_stroke_2 as textStroke2,
            b.text_uppercase_1 as textUpperCase1, b.text_uppercase_2 as textUpperCase2,
            b.position, b.image
            FROM banner b;
        """,
        nativeQuery = true
    )
    Page<IBannerDTO> findAllWithPaging(Pageable pageable);

    @Query(
        value = """
            SELECT b.id,
            b.text_stroke_1 as textStroke1, b.text_stroke_2 as textStroke2,
            b.text_uppercase_1 as textUpperCase1, b.text_uppercase_2 as textUpperCase2,
            b.position, b.image
            FROM banner b
            LIMIT 100;
        """,
        nativeQuery = true
    )
    List<IBannerDTO> findAllNonPaging();

    @Query(
        value = """
            SELECT b.id as id,
            b.text_stroke_1 as textStroke1, b.text_stroke_2 as textStroke2,
            b.text_uppercase_1 as textUpperCase1, b.text_uppercase_2 as textUpperCase2,
            b.position, b.image
            FROM banner b
            WHERE b.id = ?1;
        """,
        nativeQuery = true
    )
    Optional<IBannerDTO> findIBannerById(Integer bannerId);

    @Query(value = "SELECT * FROM banner b WHERE b.position = ?1 LIMIT 1;", nativeQuery = true)
    Optional<Banner> findBannerByPosition(String position);
}
