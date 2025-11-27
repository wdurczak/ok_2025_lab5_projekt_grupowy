package pl.put.graph;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.put.graph.services.GenerateDegreesService;

@Configuration
public class GenerateConfig {

    @Bean
    public GenerateDegreesService generateDegreesService() {
        return new GenerateDegreesService();
    }
}