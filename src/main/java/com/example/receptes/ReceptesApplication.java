package com.example.receptes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReceptesApplication {

	public static void main(String[] args) {
		//Spring application var izmantot, lai paraditu majaslapu vai uztaisitu rest endpoint - var paskaties failu "HelloWorldController.java"
		//SpringApplication.run(ReceptesApplication.class, args);
		
		
		//Izdrukaa konsole visas receptes
		RecipeList recipeList = new RecipeList();

		recipeList.printRecipes();
		
		recipeList.closeConnection();
	}

}
