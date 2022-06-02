package ru.marsel.workbench.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class Roadmap extends LongIdBaseEntity{
    String title;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roadmap")
    List<Task> tasks;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "roadmap")
    Project project;
}
