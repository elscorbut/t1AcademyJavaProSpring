package ru.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.academy.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
