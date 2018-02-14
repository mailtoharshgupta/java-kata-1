package org.echocat.kata.java.part1.services.das.impl;

import org.echocat.kata.java.part1.core.model.Author;
import org.echocat.kata.java.part1.core.model.Book;
import org.echocat.kata.java.part1.services.das.IAuthorDAS;
import org.echocat.kata.java.part1.services.das.IBookDAS;
import org.echocat.kata.java.part1.services.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Harsh Gupta on {2/14/18}
 */
@Service("bookDAS")
public class BookDAS implements IBookDAS {


    private final IBookRepository bookRepository;

    private final IAuthorDAS authorDAS;

    @Autowired
    public BookDAS(IBookRepository bookRepository, IAuthorDAS authorDAS) {
        this.bookRepository = bookRepository;
        this.authorDAS = authorDAS;
    }

    @Override
    public Book save(Book book) {
        Book persisted = bookRepository.save(book);
        if(null != persisted){
            persisted.getAuthors().forEach(author -> updateAuthor(author,persisted));
        }
        return persisted;
    }

    @Override
    public Book getByISBN(String isbn) {
        return bookRepository.getByISBN(isbn);
    }

    private void updateAuthor(String email, Book book){
        Author author = authorDAS.getByEmail(email);
        if(null !=author){
           List<String> publications=  author.getPublications();
           if(null == publications){
               publications = new ArrayList<>();
           }
           publications.add(book.getIsbn());
           author.setPublications(publications);
           authorDAS.save(author);
        }

    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }
}
