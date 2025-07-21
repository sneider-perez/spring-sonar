package cl.test.authorizer.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.net.http.HttpClient;

@Configuration
@ComponentScan(basePackages = "cl.experian")
@EntityScan(basePackages = {"cl.experian"})
@EnableJpaRepositories(basePackages = {"cl.experian"})
public class AppConfig {

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }

}
