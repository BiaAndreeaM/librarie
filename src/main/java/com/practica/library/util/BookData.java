package com.practica.library.util;

import com.practica.library.model.Book;
import com.practica.library.repository.BookRepositoryJPA;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookData {

    @Bean
    // bean (method) that runs when the app starts
    CommandLineRunner commandLineRunner(BookRepositoryJPA bookRepositoryJPA) {

        return args -> {

            Book book = new Book();
            book.setName("Inteligenta emotionala");
            book.setAuthor("Daniel Hoffman");

            bookRepositoryJPA.save(book);
        };
    }
}
