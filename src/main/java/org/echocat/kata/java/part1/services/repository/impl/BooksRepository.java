package org.echocat.kata.java.part1.services.repository.impl;

import org.echocat.kata.java.part1.core.model.Book;
import org.echocat.kata.java.part1.core.model.Magazine;
import org.echocat.kata.java.part1.services.repository.IBookRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Harsh Gupta on {2/14/18}
 */
@Service("booksRepository")
public class BooksRepository implements IBookRepository {

    Map<String, Book> bookMap = new HashMap<>();

    @Override
    public Book save(Book book) {
        String isbn = (book == null) ? null : book.getIsbn();
        bookMap.put(isbn,book);
        return bookMap.get(isbn);
    }

    @Override
    public Book getByISBN(String isbn) {
        return bookMap.get(isbn);
    }

    @Override
    public List<Book> getAllBooks() {
        return (List<Book>)bookMap.values();
    }
}
