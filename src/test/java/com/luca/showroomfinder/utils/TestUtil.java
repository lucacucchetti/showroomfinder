package com.luca.showroomfinder.utils;

import com.luca.showroomfinder.error.ShowroomFinderException;

import java.util.function.Consumer;

import static org.junit.Assert.fail;

public class TestUtil {

    public static void tryAndAssertOnShowroomFinderException(Runnable tryRunnable, Consumer<RuntimeException> assertions) {
        try {
            tryRunnable.run();
            fail();
        } catch (ShowroomFinderException e) {
            assertions.accept(e);
        }
    }
}
