package receptes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan

@SpringBootApplication
public class ReceptesApplication {

	public static void main(String[] args) {
		//Spring application var izmantot, lai paraditu majaslapu vai uztaisitu rest endpoint - var paskaties failu "RecipeController.java"
		SpringApplication.run(ReceptesApplication.class, args);
		
		System.out.println("ReceptesApplication -> main");
	}

}
