package com.first.book.mapper;

import com.first.book.domain.Book;
import com.first.book.dto.BookDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface BookControlMapper {

    @AfterMapping
    default void setStatus(BookDTO.Post dto, @MappingTarget Book book) {
        book.setStatus(Book.Status.AVAILABLE);
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    Book PostDTOToEntity(BookDTO.Post post);

    @Mapping(target = "id", ignore = true)
    void PutDTOToEntity(BookDTO.Put put, @MappingTarget Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title", ignore = true)
    @Mapping(target = "subTitle", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "publisher", ignore = true)
    void PatchDTOToEntity(BookDTO.Patch patch, @MappingTarget Book book);
}
