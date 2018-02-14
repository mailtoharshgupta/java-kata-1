package org.echocat.kata.java.part1.web.bootstrap.impl;

import lombok.SneakyThrows;
import org.echocat.kata.java.part1.core.model.Author;
import org.echocat.kata.java.part1.services.repository.IAuthorRepository;
import org.echocat.kata.java.part1.web.bootstrap.IBootstrapDataLoader;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Harsh Gupta on {2/14/18}
 */
@Service("authorBootstrapDataLoader")
public class AuthorBootstrapDataLoader implements IBootstrapDataLoader {

    private static final String DELIMITER = ";";

    private final IAuthorRepository authorRepository;

    public AuthorBootstrapDataLoader(IAuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }


    @SneakyThrows
    @Override
    public void loadData(String filePath) {

        List<String> lines = Files.lines(Paths.get(filePath))
                .collect(Collectors.toList());
        List<Author> authors = lines.stream()
                .skip(1)
                .map(lineToAuthorConverter())
                .collect(Collectors.toList());
        authors.forEach(author -> authorRepository.save(author));
    }

    private Function<String, Author> lineToAuthorConverter() {
        return line -> {
            String codes[] = line.trim().split(DELIMITER);
            return Author
                    .builder()
                    .email(codes[0])
                    .firstName(codes[1])
                    .lastName(codes[2])
                    .build();
        };
    }
}
