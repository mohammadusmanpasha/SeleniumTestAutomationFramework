package com.ctl.it.qa.staf;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TestEnvironment {
	Environment value() default Environment.E2E;
}
