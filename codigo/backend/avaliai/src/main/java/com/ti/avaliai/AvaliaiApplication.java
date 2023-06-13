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
		factory.setResources(new Resource[]{
				new ClassPathResource("data.json"),
				new ClassPathResource("academic_mail_domain.json")
		});
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
					.courseHashId("1f061de68a7a0da8378fd30974dd1a98")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());
		};
	}


}
