package com.luca.showroomfinder.user;

import com.luca.showroomfinder.utils.TestUtil;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserAdministratorTest {

    private UserAdministrator userAdministrator = new UserAdministrator();

    private User newUser = new User();
//TODO: Move register actions to user. Return a RegisteredUser.
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
        TestUtil.tryAndAssertOnShowroomFinderException(() -> userAdministrator.registerNewModerator(newUser), e -> {
            assertThat(e.getMessage()).isEqualTo(UserAdministrator.USER_NOT_REGISTERED);
            assertThat(userAdministrator.isModerator(newUser)).isFalse();
        });
    }

    @Test
    public void aUserCannotBeRegisteredTwiceTest() {
        userAdministrator.registerNewUser(newUser);
        TestUtil.tryAndAssertOnShowroomFinderException(() -> userAdministrator.registerNewUser(newUser), e -> {
            assertThat(e.getMessage()).isEqualTo(UserAdministrator.USER_ALREADY_REGISTERED);
            assertThat(userAdministrator.isRegisteredUser(newUser)).isTrue();
        });
    }

    @Test
    public void aUserCannotBeUnregisteredTwiceTest() {
        userAdministrator.registerNewUser(newUser);
        userAdministrator.unregisterUser(newUser);
        TestUtil.tryAndAssertOnShowroomFinderException(() -> userAdministrator.unregisterUser(newUser), e -> {
            assertThat(e.getMessage()).isEqualTo(UserAdministrator.USER_NOT_REGISTERED);
            assertThat(userAdministrator.isRegisteredUser(newUser)).isFalse();
        });
    }

    @Test
    public void aModeratorCannotBeRegisteredTwiceTest() {
        userAdministrator.registerNewUser(newUser);
        userAdministrator.registerNewModerator(newUser);
        TestUtil.tryAndAssertOnShowroomFinderException(() -> userAdministrator.registerNewModerator(newUser), e -> {
            assertThat(e.getMessage()).isEqualTo(userAdministrator.MODERATOR_ALREADY_REGISTERED);
            assertThat(userAdministrator.isModerator(newUser)).isTrue();
        });
    }

    @Test
    public void aModeratorCannotBeUnregisteredTwiceTest() {
        userAdministrator.registerNewUser(newUser);
        userAdministrator.registerNewModerator(newUser);
        userAdministrator.unregisterModerator(newUser);
        TestUtil.tryAndAssertOnShowroomFinderException(() -> userAdministrator.unregisterModerator(newUser), e -> {
            assertThat(e.getMessage()).isEqualTo(userAdministrator.MODERATOR_NOT_REGISTERED);
            assertThat(userAdministrator.isModerator(newUser)).isFalse();
        });
    }

}
