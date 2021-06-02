package com.pault.springmongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class SpringMongoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringMongoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository) {
		return args -> {
			Address address = new Address(
					"UK",
					"Manchester",
					"M20 6HL"
			);

			Student student = new Student(
					"Paul",
					"Thomas",
					"paul@paul.com",
					Gender.MALE,
					address,
					List.of("Maths", "Computer Science"),
					BigDecimal.TEN,
					LocalDateTime.now()

			);

			repository.insert(student);
		};
	}

}
