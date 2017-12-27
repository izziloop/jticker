package com.izziloop.jticker.module;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.izziloop.jticker.annotations.Postpone;
import com.izziloop.jticker.interceptors.Postponer;

public class PostponeModule extends AbstractModule {

    @Override
    protected void configure() {
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Postpone.class), new Postponer());
    }

}
