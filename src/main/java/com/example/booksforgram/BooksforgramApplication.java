package com.example.booksforgram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BooksforgramApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooksforgramApplication.class, args);
    }

}
