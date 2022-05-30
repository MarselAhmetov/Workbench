package ru.marsel.workbench.model.user;

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
import ru.marsel.workbench.model.LongIdBaseEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GoogleAuthData extends LongIdBaseEntity {
    String refreshToken;
    String accessToken;
    @OneToOne(fetch = FetchType.EAGER)
    User user;
}
