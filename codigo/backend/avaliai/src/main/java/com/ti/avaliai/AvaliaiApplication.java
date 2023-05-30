package com.ti.avaliai;

import com.ti.avaliai.auth.AuthenticationService;
import com.ti.avaliai.auth.dto.RegisterRequestDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

import static com.ti.avaliai.user.Role.ADMIN;
import static com.ti.avaliai.user.Role.MANAGER;


@SpringBootApplication
public class AvaliaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvaliaiApplication.class, args);
	}


	@Bean
	public Jackson2RepositoryPopulatorFactoryBean getRespositoryPopulator() {
		Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
		factory.setResources(new Resource[]{new ClassPathResource("data.json")});
		return factory;
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequestDTO.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@sga.pucminas.br")
					.password("1234")
					.universityHashId("543b45c583bfff6c30e44a751103a24f")
					.courseHashId("eb5ed7359d0bc0df70e6b7abf8584c5e")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequestDTO.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("manager@mail.com")
					.password("password")
					.role(MANAGER)
					.universityHashId("543b45c583bfff6c30e44a751103a24f")
					.courseHashId("eb5ed7359d0bc0df70e6b7abf8584c5e")
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

		};
	}


}
