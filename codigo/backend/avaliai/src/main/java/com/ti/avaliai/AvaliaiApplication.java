package com.ti.avaliai;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ti.avaliai.utils.LocalDateTimeAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;


@SpringBootApplication
public class AvaliaiApplication {

	public static void main(String[] args) {

		SpringApplication.run(AvaliaiApplication.class, args);

		Gson gson = new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
				.create();

	}


//	@Bean
//	public CommandLineRunner commandLineRunner(
//			AuthenticationService service
//	) {
//		return args -> {
//			var admin = RegisterRequestDTO.builder()
//					.firstname("Admin")
//					.lastname("Admin")
//					.email("admin@sga.pucminas.br")
//					.password("1234")
//					.universityHashId("543b45c583bfff6c30e44a751103a24f")
//					.courseHashId("1f061de68a7a0da8378fd30974dd1a98")
//					.role(ADMIN)
//					.build();
//			System.out.println("Admin token: " + service.register(admin).getAccessToken());
//
//			var manager = RegisterRequestDTO.builder()
//					.firstname("Admin")
//					.lastname("Admin")
//					.email("manager@sga.pucminas.br")
//					.password("password")
//					.role(MANAGER)
//					.universityHashId("e6379fe087f9853f4c55a6bcb3f22093")
//					.courseHashId("eb5ed7359d0bc0df70e6b7abf8584c5e")
//					.build();
//			System.out.println("Manager token: " + service.register(manager).getAccessToken());
//		};
//	}


}
