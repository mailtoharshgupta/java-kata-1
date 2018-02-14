package org.echocat.kata.java.part1.web.bootstrap.impl;

import lombok.SneakyThrows;
import org.echocat.kata.java.part1.core.model.Magazine;
import org.echocat.kata.java.part1.services.das.IMagazineDAS;
import org.echocat.kata.java.part1.services.repository.IMagazineRepository;
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
@Service("magazineBootstrapDataLoader")
public class MagazineBootstrapDataLoader implements IBootstrapDataLoader {

    private static final String DELIMITER = ";";

    private final IMagazineDAS magazineDAS;

    public MagazineBootstrapDataLoader(IMagazineDAS magazineDAS){
        this.magazineDAS = magazineDAS;
    }


    @SneakyThrows
    @Override
    public void loadData(String filePath) {


        List<String> lines = Files.lines(Paths.get(filePath))
                .collect(Collectors.toList());
        List<Magazine> magazines = lines.stream()
                .skip(1)
                .map(lineToMagazineConverter())
                .collect(Collectors.toList());
        magazines.forEach(magazine -> magazineDAS.save(magazine));
    }
    private Function<String, Magazine> lineToMagazineConverter() {
        return line -> {
            String codes[] = line.trim().split(DELIMITER);
            return Magazine
                    .builder()
                    .title(codes[0])
                    .authors(Arrays.asList(codes[1].split(",")))
                    .publishedAt(codes[2])
                    .isbn(codes[3])
                    .build();
        };
    }
}
