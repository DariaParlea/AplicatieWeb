package web.app.project.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication//(scanBasePackages = "web.app.project.project.entities")
//@EntityScan(basePackages ={"web.app.project.project.entities", "web.app.project.project.repositories", "web.app.project.project.service", "web.app.project.project.controllers"})
//@EnableJpaRepositories(basePackages = "web.app.project.project.repositories")
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);

	}

//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}

}
