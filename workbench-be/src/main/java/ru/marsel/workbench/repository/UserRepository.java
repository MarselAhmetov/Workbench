package ru.marsel.workbench.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.marsel.workbench.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}
