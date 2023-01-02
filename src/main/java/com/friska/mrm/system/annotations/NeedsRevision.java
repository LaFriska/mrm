package com.friska.mrm.system.annotations;

import javax.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Targets labeled with this means that the code needs to be revised, and reworked, meaning that it might not function as intended.
 * **/
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
@Retention(RetentionPolicy.SOURCE)
public @interface NeedsRevision {
    @Nullable String[] value() default "";
}
