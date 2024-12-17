package com.first.news.dto;

import lombok.*;

public class NewsDto {

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post{
        private String title;
        private String content;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch{
        private String title;
        private String content;
    }
}
