package security.contrparties.investigations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"security.contrparties.investigations"})
public class IasCounterpartyInvestigSprBootServer extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(IasCounterpartyInvestigSprBootServer.class, args);


    }

    /**
     * Требуется для инициализации диспетчера - сервлета без использовния xml файла
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(IasCounterpartyInvestigSprBootServer.class);
    }

}
