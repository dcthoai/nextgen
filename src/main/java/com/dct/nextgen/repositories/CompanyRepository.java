package com.dct.nextgen.repositories;

import com.dct.nextgen.dto.mapping.ICompanyDTO;
import com.dct.nextgen.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query(
        value = """
            SELECT c.ID AS id, c.name, c.description, c.phone, c.email, c.address,
            c.logo, c.image, c.video_intro AS videoIntro,
            c.license_code AS licenseCode, c.license_date AS licenseDate, c.license_address AS licenseAddress,
            c.map, c.map_image AS mapImage, c.map_slide_text AS mapSlideText,
            c.facebook, c.instagram, c.zalo, c.youtube, c.website
            FROM company c
            ORDER BY c.created_date
            LIMIT 1;
        """,
        nativeQuery = true
    )
    Optional<ICompanyDTO> findCompany();

    @Query(value = "SELECT c.video_intro FROM company c ORDER BY c.created_date LIMIT 1;", nativeQuery = true)
    String getVideoIntro();

    @Modifying
    @Query(
        value = """
            UPDATE company com
            LEFT JOIN (SELECT c.ID FROM company c ORDER BY c.created_date LIMIT 1) sub ON com.ID = sub.ID
            SET com.video_intro = ?
            WHERE sub.ID IS NOT NULL;
        """,
        nativeQuery = true
    )
    void updateVideoIntro(String videoUrl);
}
