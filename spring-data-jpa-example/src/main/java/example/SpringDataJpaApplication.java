package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        System.out.println("Start application..");
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }
}
