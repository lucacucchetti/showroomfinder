package com.luca.showroomfinder;

import com.luca.showroomfinder.error.ShowroomFinderException;
import com.luca.showroomfinder.showroom.Showroom;

import java.util.HashSet;
import java.util.Set;

public class ShowroomFinder {

    public static final String SHOWROOM_ALREADY_PENDING_APPROVAL = "Showroom is already pending approval";

    private Set<Showroom> showrooms = new HashSet<>();

    public void newShowroom(Showroom showroom) {
        if(showrooms.contains(showroom)) {
            throw new ShowroomFinderException(SHOWROOM_ALREADY_PENDING_APPROVAL);
        }
        showroom.pendingApproval();
        showrooms.add(showroom);
    }

}
