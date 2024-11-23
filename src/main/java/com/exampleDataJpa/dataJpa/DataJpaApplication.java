package com.exampleDataJpa.dataJpa;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;

@SpringBootApplication
public class DataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataJpaApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(StudentJpaRepository studentJpaRepository,StudentIdCardRepository studentIdCardRepository){
		return  args -> {

			Faker faker = new Faker();

			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@nibm.edu",firstName,lastName);
			Student student = new Student(firstName,lastName,email,faker.number().numberBetween(18,55));
			student.addBook(new Book("clean code", LocalDateTime.now().minusDays(6)));
			student.addBook(new Book("atomic habit", LocalDateTime.now().minusMonths(6)));
			student.addBook(new Book("ego is the enemy", LocalDateTime.now().minusYears(6)));

			StudentIdCard studentIdCard = new StudentIdCard(student, "123131");

			student.setStudentIdCard(studentIdCard);

			student.enrolToCourse(new Course("spring","Computer science"));

			studentJpaRepository.save(student);

			studentIdCardRepository.findById(1L).ifPresent(System.out::println);
//			studentJpaRepository.deleteById(1L);


//			generateRandomStudent(studentJpaRepository);
//			sorting(studentJpaRepository);
//			PageRequest pageRequest = PageRequest.of(0,5,Sort.by("firstName").ascending());
//			Page<Student> page = studentJpaRepository.findAll(pageRequest);
//			System.out.println(page);

		};

	}

	private static void sorting(StudentJpaRepository studentJpaRepository) {
		Sort sort = Sort.by("firstName").ascending().and(Sort.by("age").descending()) ;
		studentJpaRepository.findAll(sort).forEach(student->{
			System.out.println(student.getFirstName() + " " + student.getAge());
		});
	}

	private static void generateRandomStudent(StudentJpaRepository studentJpaRepository) {
		Faker faker = new Faker();
		for(int i = 0;i<20;i++){
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@nibm.edu",firstName,lastName);
			Student student = new Student(firstName,lastName,email,faker.number().numberBetween(18,55));

			studentJpaRepository.save(student);
		}
	}
}
