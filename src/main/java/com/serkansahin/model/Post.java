package com.serkansahin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @Column(name = "published_at")
    @Builder.Default
    private LocalDate publishedAt=LocalDate.now();
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "category_id")
    private Long categoryId;


}
