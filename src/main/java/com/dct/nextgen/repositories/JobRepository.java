package com.dct.nextgen.repositories;

import com.dct.nextgen.dto.mapping.IJobDTO;
import com.dct.nextgen.entity.Job;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

    @Query(value = "SELECT j.id, j.name, j.description, j.contact_mail as contactMail FROM job j", nativeQuery = true)
    Page<IJobDTO> findAllWithPaging(Pageable pageable);
}
