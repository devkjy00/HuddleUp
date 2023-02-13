package jy.dev.huddleup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HuddleUpApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuddleUpApplication.class, args);
    }

    @Bean
    public CommandLineRunner test(LocalSetup localSetup) {
        return (args) -> {
//            localSetup.setup();
            localSetup.printJwt();
        };
    }
}
