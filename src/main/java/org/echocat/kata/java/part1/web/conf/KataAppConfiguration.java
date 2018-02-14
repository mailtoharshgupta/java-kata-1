package org.echocat.kata.java.part1.web.conf;

import lombok.SneakyThrows;
import org.echocat.kata.java.part1.web.bootstrap.IBootstrapDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ResourceLoader;

/**
 * @author Harsh Gupta on {2/14/18}
 */
@Configuration
public class KataAppConfiguration {

    final String basePath = "org/echocat/kata/java/part1/data/";

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    @Qualifier("authorBootstrapDataLoader")
    private IBootstrapDataLoader authorBootstrapDataLoader;

    @Autowired
    @Qualifier("bookBootstrapDataLoader")
    private IBootstrapDataLoader bookBootstrapDataLoader;

    @Autowired
    @Qualifier("magazineBootstrapDataLoader")
    private IBootstrapDataLoader magazineBootstrapDataLoader;


    @EventListener(ApplicationReadyEvent.class)
    public void loadStartUpData() {
        loadAuthors();
        loadBooks();
        loadMagazines();
    }

    @SneakyThrows
    private void loadAuthors(){
        authorBootstrapDataLoader.loadData(resourceLoader
                .getResource("classpath:"+basePath+"authors.csv")
                .getFile()
                .getPath());
    }

    @SneakyThrows
    private void loadMagazines(){
        magazineBootstrapDataLoader.loadData(resourceLoader
                .getResource("classpath:"+basePath+"magazines.csv")
                .getFile()
                .getPath());
    }

    @SneakyThrows
    private void loadBooks(){
        bookBootstrapDataLoader.loadData(resourceLoader
                .getResource("classpath:"+basePath+"books.csv")
                .getFile()
                .getPath());
    }
}
