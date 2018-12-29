package com.luca.showroomfinder.user;

import com.luca.showroomfinder.error.ShowroomFinderException;

import java.util.HashSet;
import java.util.Set;

public class UserAdministrator {

    public static final String USER_NOT_REGISTERED = "User is not currently registered";

    public static final String USER_ALREADY_REGISTERED = "User is already registered";

    public static final String MODERATOR_ALREADY_REGISTERED = "Moderator is already registered";

    public static final String MODERATOR_NOT_REGISTERED = "Moderator is not registered";

    private Set<User> registeredUsers = new HashSet<>();

    private Set<Moderator> moderators = new HashSet<>();

    public void registerNewUser(User newUser) {
        if (registeredUsers.contains(newUser)) {
            throw new ShowroomFinderException(USER_ALREADY_REGISTERED);
        }
        registeredUsers.add(newUser);
    }

    public boolean isRegisteredUser(User newUser) {
        return registeredUsers.contains(newUser);
    }

    public void unregisterUser(User user) {
        if (!registeredUsers.contains(user)) {
            throw new ShowroomFinderException(USER_NOT_REGISTERED);
        }
        registeredUsers.remove(user);
    }

    public void registerNewModerator(User user) {
        if (!registeredUsers.contains(user)) {
            throw new ShowroomFinderException(USER_NOT_REGISTERED);
        }
        if (isModerator(user)) {
            throw new ShowroomFinderException(MODERATOR_ALREADY_REGISTERED);
        }
        moderators.add(new Moderator(user));
    }

    public boolean isModerator(User user) {
        return moderators.stream().anyMatch(e -> e.isUser(user));
    }

    public void unregisterModerator(User user) {
        if(!isModerator(user)) {
            throw new ShowroomFinderException(MODERATOR_NOT_REGISTERED);
        }
        Moderator administrator = moderators.stream().filter(e -> e.isUser(user)).findFirst().get();
        moderators.remove(administrator);
    }
}
