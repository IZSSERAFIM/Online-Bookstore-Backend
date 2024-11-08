package org.onlinebookstore.function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@SpringBootApplication
public class FunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunctionApplication.class, args);
    }

    @Bean
    public Function<Flux<Integer[]>, Flux<Integer>> calculateTotalPrice() {
//        return e -> e.map(value -> value[0] * value[1]);
		return e -> e.map(value -> {
			int result = value[0] * value[1];
			System.out.println("Calculating: " + value[0] + " * " + value[1] + " = " + result);
			return result;
		});
	}
}
