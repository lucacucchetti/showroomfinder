package com.luca.showroomfinder.showroom;

import static com.luca.showroomfinder.showroom.ShowroomStatus.PENDING_APPROVAL;

public class Showroom {

    private ShowroomStatus showroomStatus;

    public void pendingApproval() {
        showroomStatus = PENDING_APPROVAL;
    }

    public boolean isPendingApproval() {
        return PENDING_APPROVAL.equals(showroomStatus);
    }
}
