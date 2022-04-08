package com.training.menu.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {
    private String categoryId;
    private String categoryName;
    private String categoryImageURL;
}
