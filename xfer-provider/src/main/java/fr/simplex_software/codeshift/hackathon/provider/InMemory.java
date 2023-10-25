package fr.simplex_software.codeshift.hackathon.provider;

import org.mapstruct.*;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Qualifier
@Retention(RUNTIME)
@Target({FIELD, TYPE, METHOD})
public @interface InMemory
{
}
