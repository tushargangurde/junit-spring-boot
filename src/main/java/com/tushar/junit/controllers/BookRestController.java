package com.tushar.junit.controllers;

import com.tushar.junit.controllers.models.Book;
import com.tushar.junit.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/addBook", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        String message;
        Boolean isSaved = bookService.saveBook(book);
        if (isSaved) {
            message = "Book Saved";
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } else {
            message = "Failed to save";
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBook/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable Integer bookId) {
        Book retrievedBook = bookService.getBook(bookId);
        return new ResponseEntity<Book>(retrievedBook, HttpStatus.FOUND);
    }

    @PutMapping(value = "/updateBook/{bookId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Book> updateBook(@PathVariable Integer bookId, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(bookId, book);
        return new ResponseEntity<Book>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer bookId) {
        Book deletedBook = bookService.deleteBook(bookId);
        return new ResponseEntity<>(deletedBook, HttpStatus.OK);
    }
}

