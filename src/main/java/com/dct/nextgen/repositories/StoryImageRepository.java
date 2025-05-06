package com.dct.nextgen.repositories;

import com.dct.nextgen.dto.mapping.IStoryImageDTO;
import com.dct.nextgen.entity.StoryImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryImageRepository extends JpaRepository<StoryImage, Integer> {

    @Query(value = "SELECT si.url FROM story_image si WHERE si.story_id = ?1 ORDER BY si.position", nativeQuery = true)
    List<String> findAllImageByStoryIdOrderByPosition(Integer storyId);

    @Query(
        value = """
            SELECT si.id, si.story_id as storyId, si.url, si.position
            FROM story_image si
            WHERE si.story_id = ?1
            ORDER BY si.position
        """,
        nativeQuery = true
    )
    List<IStoryImageDTO> findAllByStoryIdOrderByPosition(Integer storyId);
}
