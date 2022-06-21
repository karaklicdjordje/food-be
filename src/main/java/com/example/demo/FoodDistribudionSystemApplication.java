package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition (info =
@Info(
          title = "FOOD DISTRIBUTION SYSTEM APPLICATION",
          version = "1.0",
          description = "Web aplikacija za upravljanje i raspodelom otpadne hrane u ugostiteljskim objektima. Ugostiteljski objekti na dnevnom nivou oglasavaju kolikom kolicinom otpadne hrane raspolazu, cena preuzimanja po kolicini, lokacija i vremenu preuzimanja",
          license = @License(name = "FTN", url = "http://ftn.uns.ac.rs/691618389/fakultet-tehnickih-nauka"),
          contact = @Contact(url = "http://localhost:8080//api/v1/fooddistribution/restaurants", name = "Food distribution", email = "fooddistribution2022@gmail.com")
  )
)
public class FoodDistribudionSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodDistribudionSystemApplication.class, args);
	}

}
