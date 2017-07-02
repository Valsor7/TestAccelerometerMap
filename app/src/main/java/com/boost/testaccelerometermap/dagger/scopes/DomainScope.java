package com.boost.testaccelerometermap.dagger.scopes;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by yaroslav on 02.07.17.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface DomainScope {
}
