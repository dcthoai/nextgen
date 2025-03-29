package com.dct.nextgen.repositories;

import com.dct.nextgen.dto.mapping.IContactFormDTO;
import com.dct.nextgen.entity.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactFormRepository extends JpaRepository<ContactForm, Integer> {

    @Query(value = "SELECT c.id, c.title, c.text1, c.text2 FROM contact_form c LIMIT 1", nativeQuery = true)
    Optional<IContactFormDTO> findContactForm();

    @Query(value = "SELECT * FROM contact_form LIMIT 1", nativeQuery = true)
    Optional<ContactForm> findContactFormDetail();
}
