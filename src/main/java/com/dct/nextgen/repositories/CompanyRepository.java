package com.dct.nextgen.repositories;

import com.dct.nextgen.dto.mapping.ICompanyDTO;
import com.dct.nextgen.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
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
            LIMIT 1 OFFSET 0
        """,
        nativeQuery = true
    )
    Optional<ICompanyDTO> findCompany();
}
