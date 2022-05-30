package ru.marsel.workbench.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marsel.workbench.model.user.GoogleAuthData;

public interface GoogleAuthDataRepository extends JpaRepository<GoogleAuthData, Long> {
    GoogleAuthData findByUserId(Long userId);
}
