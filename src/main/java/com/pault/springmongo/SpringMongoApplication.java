package com.pault.springmongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

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
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
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


			if (repository.findStudentByEmail(student.getEmail()) != null) {
				System.out.println("Student already exists : "+student.getEmail());
			} else {
				repository.insert(student);
			}


			/*
			System.out.println("-----------------------------------------------");
			String email = "paul@paul.com";
			Query query = new Query();
			query.addCriteria(Criteria.where("email").is(email));
			List<Student> list = mongoTemplate.find(query, Student.class);
			System.out.println(list);
			System.out.println("-----------------------------------------------");
			*/

			// Cut down version
			String email = "paul@paul.com";
			repository.findStudentByEmail(email).ifPresentOrElse(studentToFind -> {
				System.out.println("Found..."+studentToFind);
				// else
			}, ()-> {
				System.out.println("*** Not Found ***");
			});

		};
	}

}
