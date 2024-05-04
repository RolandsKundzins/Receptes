package com.example.receptes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ReceptesApplication {

	public static void main(String[] args) throws Exception {
		//Spring application var izmantot, lai paraditu majaslapu vai uztaisitu rest endpoint - var paskaties failu "HelloWorldController.java"
		SpringApplication.run(ReceptesApplication.class, args);
		
		System.out.println("ReceptesApplication -> main");
		//Izdrukaa konsole visas receptes
//		RecipeList recipeList = new RecipeList();
//
//		recipeList.printRecipes();
//		
//		recipeList.closeConnection();
	}

}
