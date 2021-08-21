package com.tushar.junit.services;

import com.tushar.junit.controllers.models.Book;
import com.tushar.junit.exception.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private List<Book> bookList = Arrays.asList(
            new Book(1, "Spring Core", 450.0),
            new Book(2, "Spring Boot", 350.0),
            new Book(3, "Core Java", 200.0),
            new Book(4, "Advanced Java", 300.0)
    );

    public Boolean saveBook(Book book) {
        return true;
    }

    public Book getBook(Integer bookId) {
        Optional<Book> bookFound = bookList.stream().filter(book -> book.getBookId() == bookId).findFirst();
        if (bookFound.isEmpty()) {
            throw new BookNotFoundException("Book with " + bookId + " not found");
        } else {
            return bookFound.get();
        }
    }

    public Book updateBook(Integer bookId, Book book) {
        Book bookToBeUpdate = getBook(bookId);
        if (bookToBeUpdate == null) {
            throw new BookNotFoundException("Book with " + bookId + " not found");
        } else {
            bookToBeUpdate.setBookName(book.getBookName());
            bookToBeUpdate.setBookPrice(book.getBookPrice());
            return bookToBeUpdate;
        }
    }

    public Book deleteBook(Integer bookId) {
        Book bookToBeDeleted = getBook(bookId);
        if (bookToBeDeleted == null) {
            throw new BookNotFoundException("Book with " + bookId + " not found");
        } else {
            bookList.remove(bookToBeDeleted);
        }
        return bookToBeDeleted;
    }
}
