package pl.kul.todoapp;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TodoAppApplication {
    public  static ApplicationContext ctx;
    public static void main(String[] args) {
        ctx = SpringApplication.run(TodoAppApplication.class, args);
        Application.launch(FxApplication.class, args);
    }

}
