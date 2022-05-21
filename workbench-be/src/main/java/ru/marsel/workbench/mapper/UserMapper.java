package ru.marsel.workbench.mapper;

import org.mapstruct.Mapper;
import ru.marsel.workbench.model.user.User;
import ru.model.workbench.model.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
}
