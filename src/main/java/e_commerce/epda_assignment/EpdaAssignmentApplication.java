package e_commerce.epda_assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = "e_commerce.epda_assignment")
public class EpdaAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpdaAssignmentApplication.class, args);
    }

}
