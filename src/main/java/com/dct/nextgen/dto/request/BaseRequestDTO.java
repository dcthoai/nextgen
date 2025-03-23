package com.dct.nextgen.dto.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

/**
 * Contains common information for all requests
 * @author thoaidc
 */
@SuppressWarnings("unused")
public class BaseRequestDTO {

    private String deviceID;
    private Integer page = 0;
    private Integer size = 0;
    private String sort = "";

    public Pageable getPageable() {
        if (page == null || size == null || page < 0 || size <= 0) {
            return Pageable.unpaged();
        }

        if (!StringUtils.hasText(sort)) {
            return PageRequest.of(page, size);
        }

        String[] sortArray = sort.split(",");
        String sortField = sortArray[0].trim();
        Sort.Direction sortDirection = Sort.Direction.ASC;

        if (sortArray.length > 1) {
            String direction = sortArray[1].trim();

            if ("desc".equalsIgnoreCase(direction)) {
                sortDirection = Sort.Direction.DESC;
            }
        }

        return PageRequest.of(page, size, Sort.by(sortDirection, sortField));
    }

    public BaseRequestDTO() {}

    public BaseRequestDTO(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
