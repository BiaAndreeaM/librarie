package com.practica.library.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practica.library.model.Book;
import com.practica.library.model.BookDTO;
import com.practica.library.repository.BookRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceJPA {

    private final BookRepositoryJPA bookRepositoryJPA;
    private final ObjectMapper mapper;

    public BookServiceJPA(BookRepositoryJPA bookRepositoryJPA, ObjectMapper mapper) {
        this.bookRepositoryJPA = bookRepositoryJPA;
        this.mapper = mapper;
    }

    public List<BookDTO> getBooks() {

        return bookRepositoryJPA.findAll()
                .stream()
                .map(book -> mapper.convertValue(book, BookDTO.class))
                .toList();
    }

    public BookDTO saveBook(Book book) {

        return mapper.convertValue(bookRepositoryJPA.save(book), BookDTO.class);
    }
    public List<BookDTO> getBooksByAuthor(String authorName) {
        List<Book> books = bookRepositoryJPA.findByAuthorIgnoreCase(authorName);
        return books.stream()
                .map(book -> mapper.convertValue(book, BookDTO.class))
                .toList();
    }

}
