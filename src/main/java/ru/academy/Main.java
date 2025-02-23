package ru.academy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.academy.domain.User;
import ru.academy.service.UserService;

@ComponentScan
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        UserService userService = context.getBean(UserService.class);

        User newUser1 = new User("Peter");
        userService.create(newUser1);
        User newUser2 = new User("John");
        userService.create(newUser2);
        User newUser3 = new User("Kate");
        userService.create(newUser3);

        User foundUser1 = userService.findById(newUser1.getId());
        System.out.println("found user: " + foundUser1);

        int deletedUserCount = userService.deleteById(newUser2.getId());
        System.out.println("deleted users count: " + deletedUserCount);

        userService.updateUsernameById(newUser1.getId(), "New Peter");

        userService.findAll().forEach(System.out::println);
    }
}