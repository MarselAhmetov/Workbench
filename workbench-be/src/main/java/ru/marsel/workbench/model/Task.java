package ru.marsel.workbench.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @ManyToMany(fetch = FetchType.LAZY)
    List<Task> parents;

    public Task(TaskType type) {
        this.type = type;
    }
}
