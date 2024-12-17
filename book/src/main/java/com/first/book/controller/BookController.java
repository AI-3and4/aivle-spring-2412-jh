package com.first.book.controller;

import com.first.book.domain.Book;
import com.first.book.dto.BookDTO;
import com.first.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    public final BookService bookService;

    @GetMapping
    public List<BookDTO.Response> getBooks() {
        return bookService.findBooks();
    }

    @GetMapping("/{bookId}")
    public BookDTO.Response getBook(@PathVariable("bookId") Long id) {
        return bookService.findBook(id);
    }

    @PostMapping
    public Book insertBook(@Valid @RequestBody BookDTO.Post post) {
        return bookService.insertBook(post);
    }

    @PatchMapping("/{id}/rollback")
    public Book rollbackBook(@PathVariable("id") Long id, @RequestBody BookDTO.Rollback rollback) {
        return bookService.updateBookTitleAndPublisher(id, rollback);
    }

    @PatchMapping("/{bookId}")
    public Book updateBookStatus(@PathVariable("bookId") Long id, @Valid @RequestBody BookDTO.Patch patch) {
       return bookService.updateBookStatus(id, patch);
    }

    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable("bookId") Long id, @Valid @RequestBody BookDTO.Put put) {
        return bookService.updateBook(id, put);
    }

    @DeleteMapping("/{bookId}")
    public Book deleteBook(@PathVariable("bookId") Long id) {
        bookService.deleteBook(id);
    }

}
