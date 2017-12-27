package com.izziloop.jticker;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.izziloop.jticker.annotations.Postpone;
import com.izziloop.jticker.module.PostponeModule;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class PostponeTest {

    private static final int POSTPONE_DURATION = 1000;

    private static final int INITIAL_VALUE = 0;

    private static final int UPDATED_VALUE = 2;

    @Inject
    private PostponeTester tester;

    @Test
    public void testPostpone() throws InterruptedException {
        Injector injector = Guice.createInjector(new PostponeModule());
        injector.injectMembers(this);

        tester.setValuePostponed();
        assertThat(tester.getValue()).isEqualTo(INITIAL_VALUE);

        Thread.sleep(POSTPONE_DURATION * 2);
        assertThat(tester.getValue()).isEqualTo(UPDATED_VALUE);
    }

    static class PostponeTester {

        private int value = INITIAL_VALUE;

        @Postpone(duration = POSTPONE_DURATION)
        public void setValuePostponed() {
            value = UPDATED_VALUE;
        }

        private int getValue() {
            return value;
        }

    }

}
