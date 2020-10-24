package com.interview.walrus.twitterapp.WebApplication;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.interview.walrus.twitterapp.*"})
@EntityScan("com.interview.walrus.twitterapp.Entity")
@EnableJpaRepositories("com.interview.walrus.twitterapp.Repository")
public class TwitterWebApp {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(TwitterWebApp.class);
        SpringApplication.run(TwitterWebApp.class, args);

        logger.info("Twitter Application has started");

    }
}
