package com.rechnungswesen.app;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(
		scanBasePackages = "com.rechnungswesen"
)
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
//		System.out.println("hello");
//		Predicate<Integer> isGreaterThanTwo = x -> x > 2;
//
//		System.out.println(isGreaterThanTwo.test(3));
//		Function<Integer, Integer> customSum = x -> x * x;
//		System.out.println(customSum.apply(2));
//
//		List<Integer> numbers = Arrays.asList(10, 15, 20, 25, 30);
//		Map<Boolean, List<Integer>> result = numbers.stream().collect(Collectors.partitioningBy(n -> n > 15));
//		System.out.println(result);
	}

}
