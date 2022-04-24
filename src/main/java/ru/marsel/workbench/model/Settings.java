package ru.marsel.workbench.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Settings extends LongIdBaseEntity{
    @OneToOne(fetch = FetchType.EAGER)
    User user;
}
