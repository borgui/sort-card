package com.guillaume.sortcard;

import com.guillaume.sortcard.controller.CardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableSwagger2
public class SortCardApplication {

	@Autowired
	CardController cardController;

	public static void main(String[] args) {
		SpringApplication.run(SortCardApplication.class, args);
	}

}
