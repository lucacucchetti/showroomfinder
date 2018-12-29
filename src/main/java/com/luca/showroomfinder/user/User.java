package com.luca.showroomfinder.user;

import com.luca.showroomfinder.showroom.Showroom;
import com.luca.showroomfinder.ShowroomFinder;

public class User {

    public void submitShowroom(ShowroomFinder showroomFinder, Showroom showroom) {
        showroomFinder.newShowroom(showroom);
    }
}
