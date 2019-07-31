package training.model.entities.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    Client, Admin;

    @Override
    public String getAuthority() {
        return name();
    }
}
