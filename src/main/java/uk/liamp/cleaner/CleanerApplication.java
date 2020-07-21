package uk.liamp.cleaner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Application class that contains the entry point to the application and runs spring boot.
 * 
 * @author liampuk
 */
@SpringBootApplication
public class CleanerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleanerApplication.class, args);
	}

}
