package com.jurijz;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by jurijz on 1/5/2019.
 */
@SpringBootApplication
public class DecathlonApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(DecathlonApp.class)
                .run(args);
    }
}
