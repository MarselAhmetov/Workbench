package ru.marsel.workbench.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task extends LongIdBaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    TaskType type;
    @Enumerated(EnumType.STRING)
    TaskStatus status = TaskStatus.LOCKED;
    @ManyToMany(fetch = FetchType.LAZY)
    List<Task> parents;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roadmap_id")
    Roadmap roadmap;
    String trelloCardId;

    public Task(TaskType type) {
        this.type = type;
    }
}
