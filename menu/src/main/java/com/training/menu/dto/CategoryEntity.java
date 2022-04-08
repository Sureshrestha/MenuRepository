package com.training.menu.dto;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(schema = "menu.category")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "category_id", nullable = false, unique = true)
    private String categoryId;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "category_image_url", length = 1023)
    private String categoryImageURL;

}
