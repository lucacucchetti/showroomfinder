package com.luca.showroomfinder.user;

import com.luca.showroomfinder.error.ShowroomFinderException;
import org.junit.Test;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class UserAdministratorTest {

    private UserAdministrator userAdministrator = new UserAdministrator();

    private User newUser = new User();

    @Test
    public void aUserCanRegisterTest() {
        userAdministrator.registerNewUser(newUser);
        assertThat(userAdministrator.isRegisteredUser(newUser)).isTrue();
    }

    @Test
    public void aUserCanUnregisterTest() {
        userAdministrator.registerNewUser(newUser);
        userAdministrator.unregisterUser(newUser);
        assertThat(userAdministrator.isRegisteredUser(newUser)).isFalse();
    }

    @Test
    public void aModeratorCanBeRegisteredTest() {
        userAdministrator.registerNewUser(newUser);
        userAdministrator.registerNewModerator(newUser);
        assertThat(userAdministrator.isModerator(newUser)).isTrue();
    }

    @Test
    public void aModeratorCanBeRemovedTest() {
        userAdministrator.registerNewUser(newUser);
        userAdministrator.registerNewModerator(newUser);
        userAdministrator.unregisterModerator(newUser);

        assertThat(userAdministrator.isModerator(newUser)).isFalse();
    }

    @Test
    public void aModeratorCannotBeRegisteredWithoutBeingARegisteredUserTest() {
        tryAndAssertOnShowroomFinderException(() -> userAdministrator.registerNewModerator(newUser), e -> {
            assertThat(e.getMessage()).isEqualTo(UserAdministrator.USER_NOT_REGISTERED);
            assertThat(userAdministrator.isModerator(newUser)).isFalse();
        });
    }

    @Test
    public void aUserCannotBeRegisteredTwiceTest() {
        userAdministrator.registerNewUser(newUser);
        tryAndAssertOnShowroomFinderException(() -> userAdministrator.registerNewUser(newUser), e -> {
            assertThat(e.getMessage()).isEqualTo(UserAdministrator.USER_ALREADY_REGISTERED);
            assertThat(userAdministrator.isRegisteredUser(newUser)).isTrue();
        });
    }

    @Test
    public void aUserCannotBeUnregisteredTwiceTest() {
        userAdministrator.registerNewUser(newUser);
        userAdministrator.unregisterUser(newUser);
        tryAndAssertOnShowroomFinderException(() -> userAdministrator.unregisterUser(newUser), e -> {
            assertThat(e.getMessage()).isEqualTo(UserAdministrator.USER_NOT_REGISTERED);
            assertThat(userAdministrator.isRegisteredUser(newUser)).isFalse();
        });
    }

    @Test
    public void aModeratorCannotBeRegisteredTwiceTest() {
        userAdministrator.registerNewUser(newUser);
        userAdministrator.registerNewModerator(newUser);
        tryAndAssertOnShowroomFinderException(() -> userAdministrator.registerNewModerator(newUser), e -> {
            assertThat(e.getMessage()).isEqualTo(userAdministrator.MODERATOR_ALREADY_REGISTERED);
            assertThat(userAdministrator.isModerator(newUser)).isTrue();
        });
    }

    @Test
    public void aModeratorCannotBeUnregisteredTwiceTest() {
        userAdministrator.registerNewUser(newUser);
        userAdministrator.registerNewModerator(newUser);
        userAdministrator.unregisterModerator(newUser);
        tryAndAssertOnShowroomFinderException(() -> userAdministrator.unregisterModerator(newUser), e -> {
            assertThat(e.getMessage()).isEqualTo(userAdministrator.MODERATOR_NOT_REGISTERED);
            assertThat(userAdministrator.isModerator(newUser)).isFalse();
        });
    }

    private void tryAndAssertOnShowroomFinderException(Runnable tryRunnable, Consumer<RuntimeException> assertions) {
        try {
            tryRunnable.run();
            fail();
        } catch (ShowroomFinderException e) {
            assertions.accept(e);
        }
    }

}
