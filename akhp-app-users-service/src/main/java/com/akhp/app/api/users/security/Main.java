package com.akhp.app.api.users.security;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {

		List<Integer> numbers = Arrays.asList(5, 3, 8, 6, 3, 5, 2, 1, 8);

		Optional<Integer> findFirst = numbers.stream().sorted()
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
				.entrySet().stream().filter(entry -> entry.getValue() == 1L).map(e -> e.getKey()).findFirst();
		System.out.println(findFirst.get());

	}
}
