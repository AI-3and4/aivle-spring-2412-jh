package com.first.news.domain;

import com.first.news.dto.NewsDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    // MapStruct로 대체
//    public static News toEntity(NewsDto.Post post) {
//        return new News(null, post.getTitle(), post.getContent());
//    }
}
