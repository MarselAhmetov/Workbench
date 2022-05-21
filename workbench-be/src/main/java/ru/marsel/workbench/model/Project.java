package ru.marsel.workbench.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.marsel.workbench.model.user.User;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Project extends LongIdBaseEntity {
    String name;
    String description;
    @OneToOne(fetch = FetchType.EAGER)
    Roadmap roadmap;
    @ManyToOne(fetch = FetchType.EAGER)
    User user;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "project_attributes_relations",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    List<ProjectAttribute> attribute;
}
