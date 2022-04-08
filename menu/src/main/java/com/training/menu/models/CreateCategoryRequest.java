package com.training.menu.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCategoryRequest {
    private String categoryName;
    private String categoryImageURL;
}
