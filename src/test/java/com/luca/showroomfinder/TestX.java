package com.luca.showroomfinder;

import com.luca.showroomfinder.showroom.Showroom;
import com.luca.showroomfinder.user.Moderator;
import com.luca.showroomfinder.user.User;
import com.luca.showroomfinder.user.UserAdministrator;
import com.luca.showroomfinder.utils.TestUtil;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestX {

    private User user = new User();

    private final User moderatorUser = new User();

    private Moderator moderator = new Moderator(moderatorUser);

    private UserAdministrator userAdministrator = new UserAdministrator();

    private Showroom showroom = new Showroom();

    private ShowroomFinder showroomFinder = new ShowroomFinder();

    @Before
    public void setUp() {
        userAdministrator.registerNewUser(user);
        userAdministrator.registerNewUser(moderatorUser);
        userAdministrator.registerNewModerator(moderatorUser);
    }

    @Test
    public void aRegisteredUserCanSubmitAShowroomForApprovalTest() {
        user.submitShowroom(showroomFinder, showroom);
        assertThat(showroom.isPendingApproval()).isTrue();
    }

    @Test
    public void aShowroomCannotBeSubmittedTwiceTest() {
        user.submitShowroom(showroomFinder, showroom);
        TestUtil.tryAndAssertOnShowroomFinderException(() -> user.submitShowroom(showroomFinder, showroom), e -> {
            assertThat(e.getMessage()).isEqualTo(ShowroomFinder.SHOWROOM_ALREADY_PENDING_APPROVAL);
            assertThat(showroom.isPendingApproval()).isTrue();
        });
    }

    //TODO: Not submitting a showroom twice only if it's the same user submitting, but counting distinct submissions.

    @Test
    public void aModeratorCanApproveAShowroomTest() {

    }

    @Test
    public void aRegisteredUserCanReportAShowroomTest() {

    }

    @Test
    public void aRegisteredUserCannotReportAShowroomTwiceTest() {

    }

    @Test
    public void aModeratorCanShutDownAPendingApprovalShowroomTest() {

    }

    @Test
    public void aModeratorCanShutDownAnApprovedShowroomTest() {

    }

    @Test
    public void aRegisteredUserCanLookForShowroomsNearHimTest() {

    }

    @Test
    public void aRegisteredUserCanLookForShowroomsInWantedLocationTest() {

    }
}
