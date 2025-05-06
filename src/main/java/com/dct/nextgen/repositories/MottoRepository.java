package com.dct.nextgen.repositories;

import com.dct.nextgen.dto.mapping.IMottoDTO;
import com.dct.nextgen.entity.Motto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MottoRepository extends JpaRepository<Motto, Integer> {

    @Query(value = "SELECT m.id, m.title, m.description FROM motto m", nativeQuery = true)
    Page<IMottoDTO> findAllWithPaging(Pageable pageable);
}
