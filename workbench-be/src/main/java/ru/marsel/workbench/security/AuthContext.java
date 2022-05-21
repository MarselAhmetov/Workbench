package ru.marsel.workbench.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.marsel.workbench.model.user.User;
import ru.marsel.workbench.security.jwt.UserDetailsImpl;

@Component
public class AuthContext {
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetailsImpl) authentication.getPrincipal()).getUser();
    }
}
