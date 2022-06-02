package ru.marsel.workbench.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marsel.workbench.model.GoogleDriveData;

public interface GoogleDriveDataRepository extends JpaRepository<GoogleDriveData, Long> {
    GoogleDriveData findByProjectId(Long projectId);
}
