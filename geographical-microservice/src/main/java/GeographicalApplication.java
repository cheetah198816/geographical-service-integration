
import api.Resources;
import config.Configurations;
import gateway.Gateway;
import javafx.application.Application;
import mappers.Mappers;
import model.JobEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import repositories.Repositories;
import services.Services;

/**
 * Created by chetan on 22.12.2017.
 */
@SpringBootApplication
@EnableIntegration
@IntegrationComponentScan(basePackageClasses = Gateway.class)
@ComponentScan(basePackageClasses = {Mappers.class, Resources.class, Services.class, Configurations.class})
@EntityScan(basePackageClasses = JobEntity.class)
@EnableJpaRepositories(basePackageClasses = {Repositories.class})
public class GeographicalApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(GeographicalApplication.class);
    }
}
