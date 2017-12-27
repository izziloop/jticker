package com.izziloop.jticker.interceptors;

import com.izziloop.jticker.annotations.Postpone;
import com.izziloop.jticker.exceptions.MethodNotExecutedException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Postponer implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Postpone annotation = invocation.getMethod().getAnnotation(Postpone.class);
        Runnable action = new PostponedRunnable(invocation);

        return Executors.newSingleThreadScheduledExecutor().schedule(action, annotation.duration(), TimeUnit.MILLISECONDS);
    }

    private class PostponedRunnable implements Runnable {

        private MethodInvocation invocation;

        private PostponedRunnable(MethodInvocation invocation) {
            this.invocation = invocation;
        }

        @Override
        public void run() {
            try {
                invocation.proceed();
            } catch (Throwable throwable) {
                throw new MethodNotExecutedException("Method invocation failed");
            }
        }

    }

}
