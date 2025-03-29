package com.dct.nextgen.repositories;

import com.dct.nextgen.dto.mapping.IStoryDTO;
import com.dct.nextgen.entity.Story;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {

    @Query(
        value = "SELECT s.id, s.subtitle, s.title, s.text1, s.text2, s.text3, s.position FROM story s",
        nativeQuery = true
    )
    Page<IStoryDTO> findAllWithPaging(Pageable pageable);

    @Query(
        value = "SELECT s.id, s.subtitle, s.title, s.text1, s.text2, s.text3, s.position FROM story s LIMIT 20",
        nativeQuery = true
    )
    List<IStoryDTO> findAllNonPaging();

    @Query(
        value = "SELECT s.id, s.subtitle, s.title, s.position, s.text1, s.text2, s.text3 FROM story s WHERE s.position = ?1",
        nativeQuery = true
    )
    Optional<IStoryDTO> findIStoryByPosition(String position);

    Optional<Story> findByPosition(String position);
}
