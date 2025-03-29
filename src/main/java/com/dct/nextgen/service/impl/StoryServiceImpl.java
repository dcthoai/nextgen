package com.dct.nextgen.service.impl;

import com.dct.nextgen.common.Common;
import com.dct.nextgen.common.FileUtils;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.dto.about.StoryDTO;
import com.dct.nextgen.dto.about.StoryImageDTO;
import com.dct.nextgen.dto.mapping.IStoryDTO;
import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.UpdateStoryRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.entity.Story;
import com.dct.nextgen.entity.StoryImage;
import com.dct.nextgen.exception.BaseBadRequestException;
import com.dct.nextgen.repositories.StoryImageRepository;
import com.dct.nextgen.repositories.StoryRepository;
import com.dct.nextgen.service.StoryService;

import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StoryServiceImpl implements StoryService {

    private final StoryRepository storyRepository;
    private final StoryImageRepository storyImageRepository;
    private final FileUtils fileUtils;
    private static final String ENTITY_NAME = "StoryServiceImpl";

    public StoryServiceImpl(StoryRepository storyRepository,
                            StoryImageRepository storyImageRepository,
                            FileUtils fileUtils) {
        this.storyRepository = storyRepository;
        this.storyImageRepository = storyImageRepository;
        this.fileUtils = fileUtils;
    }

    @Override
    public BaseResponseDTO getAllStoriesWithPaging(BaseRequestDTO request) {
        if (request.getPageable().isPaged()) {
            Page<IStoryDTO> storiesWithPaged = storyRepository.findAllWithPaging(request.getPageable());
            return BaseResponseDTO.builder().total(storiesWithPaged.getTotalElements()).ok(storiesWithPaged.getContent());
        }

        return BaseResponseDTO.builder().ok(storyRepository.findAllNonPaging());
    }

    @Override
    public BaseResponseDTO getStoryDetail(String position) {
        Optional<Story> storyOptional = storyRepository.findByPosition(position);

        if (storyOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        Story story = storyOptional.get();
        StoryDTO storyDetail = new StoryDTO();
        BeanUtils.copyProperties(story, storyDetail);
        Common.setAuditingInfo(story, storyDetail);
        storyDetail.setStoryImages(storyImageRepository.findAllByStoryIdOrderByPosition(story.getId()));

        return BaseResponseDTO.builder().ok(storyDetail);
    }

    @Override
    public BaseResponseDTO getStory(String position) {
        Optional<IStoryDTO> storyOptional = storyRepository.findIStoryByPosition(position);

        if (storyOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        StoryDTO storyDTO = new StoryDTO();
        BeanUtils.copyProperties(storyOptional.get(), storyDTO);
        storyDTO.setImageUrls(storyImageRepository.findAllImageByStoryIdOrderByPosition(storyDTO.getId()));

        return BaseResponseDTO.builder().ok(storyDTO);
    }

    @Override
    @Transactional
    public BaseResponseDTO updateStory(UpdateStoryRequestDTO request) {
        Story story = storyRepository.findByPosition(request.getPosition()).orElseGet(Story::new);
        BeanUtils.copyProperties(request, story, "id", "images");
        List<StoryImage> updatedImages = new ArrayList<>();
        Map<Integer, StoryImage> positionMap = story.getImages().stream()
            .collect(Collectors.toMap(StoryImage::getId, Function.identity()));
        int position = 0;

        for (StoryImageDTO storyImageDTO : request.getStoryImages()) {
            StoryImage existingImage = positionMap.get(storyImageDTO.getId());

            // Đã tồn tại story image
            if (Objects.nonNull(existingImage)) {
                if (FileUtils.invalidUploadFile(storyImageDTO.getImage())) {
                    updatedImages.add(existingImage);
                } else {
                    String imageUrl = fileUtils.save(storyImageDTO.getImage());

                    if (StringUtils.hasText(imageUrl)) {
                        fileUtils.delete(existingImage.getUrl());
                        existingImage.setUrl(imageUrl);
                    }
                }

                existingImage.setPosition(position++);
            } else {
                StoryImage storyImage = new StoryImage();
                storyImage.setStory(story);
                storyImage.setUrl(fileUtils.save(storyImageDTO.getImage()));
                storyImage.setPosition(position++);
                updatedImages.add(storyImage);
            }
        }

        story.getImages().clear();
        story.getImages().addAll(updatedImages);
        storyRepository.save(story);

        return BaseResponseDTO.builder().ok();
    }
}
