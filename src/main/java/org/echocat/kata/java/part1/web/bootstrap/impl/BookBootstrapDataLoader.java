package org.echocat.kata.java.part1.web.bootstrap.impl;

import lombok.SneakyThrows;
import org.echocat.kata.java.part1.core.model.Book;
import org.echocat.kata.java.part1.services.das.IBookDAS;
import org.echocat.kata.java.part1.services.repository.IBookRepository;
import org.echocat.kata.java.part1.web.bootstrap.IBootstrapDataLoader;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Harsh Gupta on {2/14/18}
 */
@Service("bookBootstrapDataLoader")
public class BookBootstrapDataLoader implements IBootstrapDataLoader {


    private static final String DELIMITER = ";";

    private final IBookDAS bookDAS;

    public BookBootstrapDataLoader(IBookDAS bookDAS){
        this.bookDAS = bookDAS;
    }


    @SneakyThrows
    @Override
    public void loadData(String filePath) {


        List<String> lines = Files.lines(Paths.get(filePath))
                .collect(Collectors.toList());
        List<Book> books = lines.stream()
                .skip(1)
                .map(lineToAuthorConverter())
                .collect(Collectors.toList());
        books.forEach(book -> bookDAS.save(book));
    }
    private Function<String, Book> lineToAuthorConverter() {
        return line -> {
            String codes[] = line.trim().split(DELIMITER);
            return Book
                    .builder()
                    .title(codes[0])
                    .description(codes[3])
                    .authors(Arrays.asList(codes[2].split(",")))
                    .isbn(codes[1])
                    .build();
        };
    }
}
