package ru.academy.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.academy.domain.User;
import ru.academy.service.UserService;

@Slf4j
@RequiredArgsConstructor
@Component
public class StartWork  implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) {
        User newUser = new User("Liza");
        userService.create(newUser);

        User foundUser1 = userService.findById(newUser.getId());
        log.info("found user: {}", foundUser1);

        User foundUser2 = userService.findById(1L);
        userService.deleteById(foundUser2.getId());

        userService.updateUsernameById(newUser.getId(), "New Liza");

        userService.findAll().forEach((user) -> log.info(user.toString()));
    }
}
