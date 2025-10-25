package com.sujay.journalApplication.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulerTests {

    @Autowired
    private UserScheduler userScheduler;

    public void testFetchUsersAndSendSaMail () {
        userScheduler.fetchUsersAndSendSaMail();
    }
}