package com.example.backend_challenge_tecnico_techforb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.example.backend_challenge_tecnico_techforb.Repositorys")
public class BackendChallengeTecnicoTechforbApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendChallengeTecnicoTechforbApplication.class, args);
	}

}
