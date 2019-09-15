package com.wzq.high.event;

import com.wzq.model.User;
import org.springframework.context.ApplicationEvent;

public class UserRegisterEvent extends ApplicationEvent {

    private User user;

    public UserRegisterEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser(){
        return user;
    }
}
