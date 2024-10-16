package md.donesk.smartparking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SmartParkingJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartParkingJavaApplication.class, args);
    }

}
