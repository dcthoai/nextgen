package com.dct.nextgen.repositories;

import com.dct.nextgen.dto.mapping.IPartnerDTO;
import com.dct.nextgen.entity.Partner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer> {

    @Query(value = "SELECT p.id, p.name, p.logo FROM partner p", nativeQuery = true)
    Page<IPartnerDTO> findAllWithPaging(Pageable pageable);
}
