package com.sharmila.bowling.application;

import java.util.Arrays;
import java.util.concurrent.Executor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.sharmila.bowling.models.Bowler;
import com.sharmila.bowling.repositories.BowlerRepo;


@SpringBootApplication
@ComponentScan(
{ "com.sharmila.bowling.controllers", "com.sharmila.bowling.dto","com.sharmila.bowling.service" })
@EntityScan("com.sharmila.bowling.models")
@EnableJpaRepositories("com.sharmila.bowling.repositories")
@EnableAsync
public class BowlingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BowlingApplication.class, args);
	}

	@Bean
	  public Executor taskExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setCorePoolSize(2);
	    executor.setMaxPoolSize(2);
	    executor.setQueueCapacity(500);
	    executor.setThreadNamePrefix("GithubLookup-");
	    executor.initialize();
	    return executor;
	  }

//		@Bean
//		public CommandLineRunner initilazer(BowlerRepo repo)
//		{
//			System.out.println("saving objects");
//			return args -> repo.saveAll(Arrays.asList(new Bowler((long) 1, "sharmila", "deva"),
//			                                          new Bowler((long) 2, "sharru", "deva")));
//		}


}
