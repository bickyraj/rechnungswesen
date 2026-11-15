package com.rechnungswesen.app.practise;


import java.util.ArrayList;
import java.util.List;

import com.rechnungswesen.app.services.N8nLogService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonDemo {
	private N8nLogService n8nLogService;

	public PersonDemo(N8nLogService n8nLogService) {
		this.n8nLogService = n8nLogService;
	}

	public class Person {
		private int id;
		private String name;
		private int age;

		public Person(int id, String name, int age) {
			this.id = id;
			this.name = name;
			this.age = age;
		}
	}

	private List<Person> persons = new ArrayList<>();

	@PostConstruct
	public void createPersons() {
		persons.add(new Person(101, "John", 25));
		persons.add(new Person(102, "David", 30));
		persons.add(new Person(103, "Alex", 28));
		n8nLogService.sendError("Test error from Spring Boot! create person error");
	}
}

