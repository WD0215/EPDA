package e_commerce.epda_assignment.utility;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Source {
    @Bean("emf")
    public EntityManagerFactory emf(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PG_DB");
        return emf;
    }

}
