package lydia.ralph.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages="lydia.ralph.repositories")
@EntityScan("lydia.ralph.domain")
@SpringBootApplication
public class OysterCardApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(OysterCardApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
