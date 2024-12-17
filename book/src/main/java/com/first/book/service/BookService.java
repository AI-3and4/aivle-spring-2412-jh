package com.first.book.service;

import com.first.book.domain.Book;
import com.first.book.dto.BookDTO;
import com.first.book.mapper.BookControlMapper;
import com.first.book.mapper.BookResponseMapper;
import com.first.book.repository.BookRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final BookControlMapper controlMapper;
    private final BookResponseMapper responseMapper;

    public Book insertBook(BookDTO.Post post) {
        Book book = controlMapper.PostDTOToEntity(post);
        return saveBook(book);
    }


    public Book updateBook(Long id, BookDTO.Put put) {
        Book book = findVerifiedBook(id);
        controlMapper.PutDTOToEntity(put, book);
        return saveBook(book);
    }

    @Transactional
    public Book updateBookStatus(Long id, BookDTO.Patch patch) {
        Book book = findVerifiedBook(id);
        controlMapper.PatchDTOToEntity(patch, book);
        return saveBook(book);
    }

    public void updateBookTitle(Book book, String title) {
        book.setTitle(title);
        saveBook(book);
    }

    public void updateBookPublisher(Book book, String publisher) {
        book.setPublisher(publisher);
        if(true) {
            throw new RuntimeException("트랜잭션이 롤백되지 않습니다.");
        }
        saveBook(book);
    }

    public Book updateBookTitleAndPublisher(Long id, BookDTO.Rollback rollback) {
        Book book = findVerifiedBook(id);
        updateBookTitle(book, rollback.getTitle());
        updateBookPublisher(book, rollback.getPublisher());
        return book;
    }

    public void deleteBook(Long id) {
        Book book = findVerifiedBook(id);
        if (book.getStatus() == Book.Status.BOROWED) {
            throw new IllegalArgumentException("대출 중인 책은 삭제할 수 없습니다.");
        }
        bookRepository.delete(book);
    }

    public BookDTO.Response findBook(Long id) {
        Book book = findVerifiedBook(id);
        return responseMapper.entityToResponse(book);
    }

    public List<BookDTO.Response> findBooks() {
        List<Book> books = bookRepository.findAll();
        return responseMapper.booksToResponses(books);
    }


    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book findVerifiedBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 책입니다."));
    }

}
