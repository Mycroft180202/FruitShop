package com.example.fruit_api.common.api.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

@Data
@Builder
public class Paging implements Serializable {

    private int currentPage;
    private int pageSize;
    private long total;
    private int totalPages;
    private boolean isFirst;
    private boolean isLast;
    private int numberOfElements;
    private Sort sort;
    private long offset;
    private boolean paged;
}