package com.dct.nextgen.repositories;

import com.dct.nextgen.entity.StoryImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryImageRepository extends JpaRepository<StoryImage, Integer> {
}
