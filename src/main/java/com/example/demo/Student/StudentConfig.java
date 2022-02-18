package com.example.demo.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner mCommandLineRunner (
            StudentRepository repository
    ) {
        return args -> {
            Student mohammed = new Student(
                    "Mohammed",
                    LocalDate.of(1997, Month.OCTOBER, 30),
                    "mohammedhaddad1058@gmail.com"
            );
            Student ahmed = new Student(
                    "Ahmed",
                    LocalDate.of(2000, Month.FEBRUARY, 24),
                    "ahmedhaddad@gmail.com"
            );

            Student hemdan = new Student(
                    "Hemdan",
                    LocalDate.of(1993, Month.JULY, 27),
                    "hemdanhaddad@gmail.com"
            );

            repository.saveAll(
                    List.of(hemdan, mohammed, ahmed)
            );
        };
    }
}
