package ru.marsel.workbench.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marsel.workbench.model.Roadmap;

public interface RoadmapRepository extends JpaRepository<Roadmap, Long> {

}
