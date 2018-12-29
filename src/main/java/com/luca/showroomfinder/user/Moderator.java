package com.luca.showroomfinder.user;

public class Moderator {
    private User user;

    public Moderator(User user) {
        this.user = user;
    }

    public boolean isUser(User anotherUser) {
        return this.user.equals(anotherUser);
    }
}
