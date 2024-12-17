package com.first.news.mapper;

import com.first.news.domain.News;
import com.first.news.dto.NewsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NewsMapper {

    @Mapping(target = "newsId", ignore = true)
    public News newsPostDtoToNews(NewsDto.Post post);
}
