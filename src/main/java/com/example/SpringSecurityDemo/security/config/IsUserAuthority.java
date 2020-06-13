package com.example.SpringSecurityDemo.security.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('EDITOR')")
public @interface IsUserAuthority {

}

