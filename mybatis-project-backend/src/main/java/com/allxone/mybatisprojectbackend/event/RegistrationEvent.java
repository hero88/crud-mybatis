package com.allxone.mybatisprojectbackend.event;

import com.allxone.mybatisprojectbackend.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationEvent extends ApplicationEvent {
    private User user;
    private String jwtToken;
    public RegistrationEvent(User user, String jwtToken) {
        super(user);
        this.user = user;
        this.jwtToken = jwtToken;
    }
}
