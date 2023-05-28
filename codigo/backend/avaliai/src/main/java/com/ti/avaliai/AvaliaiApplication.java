package com.ti.avaliai;

import com.ti.avaliai.auth.AuthenticationService;
import com.ti.avaliai.auth.RegisterRequest;
import com.ti.avaliai.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.ti.avaliai.user.Role.ADMIN;
import static com.ti.avaliai.user.Role.MANAGER;


@SpringBootApplication
public class AvaliaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvaliaiApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@sga.pucminas.br")
					.password("1234")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("manager@mail.com")
					.password("password")
					.role(MANAGER)
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

		};
	}
}
